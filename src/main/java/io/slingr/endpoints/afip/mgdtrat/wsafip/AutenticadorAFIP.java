package io.slingr.endpoints.afip.mgdtrat.wsafip;

import io.slingr.endpoints.afip.AfipEndpoint;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.services.datastores.DataStore;
import io.slingr.endpoints.utils.Json;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.Base64;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import javax.xml.rpc.ParameterMode;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Se comunica con el WS de AFIP de Autenticación y
 * Autorización de Testing.
 *
 * @author itraverso
 */
public class AutenticadorAFIP {

    private static final Logger logger = LoggerFactory.getLogger(AfipEndpoint.class);

    private GestorDeConfiguracion gestorPropiedades;
    private TicketAccesoAFIP taAFIP;
    private DataStore configStore;

    public AutenticadorAFIP(DataStore configStore) {
        this.configStore = configStore;
        this.gestorPropiedades = GestorDeConfiguracion.getInstance();
        this.generarTicketAccesoAFIP();
    }

    /**
     * Genera el ticket de acceso AFIP.
     */
    private void generarTicketAccesoAFIP() {
        this.taAFIP = new TicketAccesoAFIP();
        try {
            String token = this.gestorPropiedades.getProperty("token");
            String sign = this.gestorPropiedades.getProperty("sign");
            String expiracionTRA = this.gestorPropiedades.getProperty("expiracionTRA");

            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(sign)) {
                // retrieve it from data store
                Json storedConfig = configStore.findOne(Json.map());
                if (storedConfig != null) {
                    logger.info("Retrieving from configuration");
                    token = storedConfig.string("token");
                    sign = storedConfig.string("sign");
                    expiracionTRA = storedConfig.string("expiracionTRA");
                }
            }
            this.taAFIP.setToken(token);
            this.taAFIP.setSign(sign);
            if (!StringUtils.isEmpty(expiracionTRA)) {
                this.taAFIP.setExpiracionTA(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S").parse(expiracionTRA));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TicketAccesoAFIP inicializar() {
        if (!this.taAFIP.estaActivoTAAFIP()) {
            logger.info("Token no esta activo... Se requiere re-autenticar");
            this.autenticacionYAutorizacion();
        }
        return this.taAFIP;
    }

    private void autenticacionYAutorizacion() {
        logger.info("Autenticando...");

        String loginTicketResponse = null;
        String endpoint = this.gestorPropiedades.getProperty("endpointAutenticacion");
        String service = this.gestorPropiedades.getProperty("service");
        String dstDN = this.gestorPropiedades.getProperty("dstdn");
        String p12file = this.gestorPropiedades.getAbsolutePathConfigurationDir() + this.gestorPropiedades.getProperty("keystore");
        String signer = this.gestorPropiedades.getProperty("keystore-signer");
        String p12pass = this.gestorPropiedades.getProperty("keystore-password");

        File fCertificadoP12 = new File(p12file);
        //Si no existe el certificado.p12 lo genero
        if (!fCertificadoP12.exists()) {
            logger.info("El store no existe... se necesita recrearlo");
            this.generarCertificadoP12();
        }

        // Set proxy system vars
        System.setProperty("http.proxyHost", this.gestorPropiedades.getProperty("http_proxy", ""));
        System.setProperty("http.proxyPort", this.gestorPropiedades.getProperty("http_proxy_port", ""));

        // Set the keystore used by SSL
        //System.setProperty("javax.net.ssl.trustStore", this.gestorConfig.getProperty("trustStore"));
        //System.setProperty("javax.net.ssl.trustStorePassword", this.gestorConfig.getProperty("trustStore_password"));

        Long ticketTime = Long.valueOf(this.gestorPropiedades.getProperty("TicketTime"));

        // Create LoginTicketRequest_xml_cms
        byte[] loginTicketRequest_xml_cms = crearCMS(p12file, p12pass, signer, dstDN, service, ticketTime);

        // Invoke AFIP wsaa and get LoginTicketResponse
        try {
            loginTicketResponse = invocarWSAA(loginTicketRequest_xml_cms, endpoint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Get token & sign from LoginTicketResponse
        try {
            Reader tokenReader = new StringReader(loginTicketResponse);
            Document tokenDoc = new SAXReader(false).read(tokenReader);

            String token = tokenDoc.valueOf("/loginTicketResponse/credentials/token");
            String sign = tokenDoc.valueOf("/loginTicketResponse/credentials/sign");
            String expiracionTRA = tokenDoc.valueOf("/loginTicketResponse/header/expirationTime");

            //Guardo la información de Ticket de Acceso en el archivo de configuración
            this.gestorPropiedades.setProperty("token", token);
            this.gestorPropiedades.setProperty("sign", sign);
            this.gestorPropiedades.setProperty("expiracionTRA", expiracionTRA);
            Json config = this.configStore.findOne(Json.map());
            if (config == null) {
                config = Json.map();
            }
            config.set("token", token);
            config.set("sign", sign);
            config.set("expiracionTRA", expiracionTRA);
            logger.info("Saving into datastore");
            this.configStore.save(config);

            this.generarTicketAccesoAFIP();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String invocarWSAA(byte[] loginTicketRequest_xml_cms, String endpoint) throws Exception {
        String loginTicketResponse;

        Service service = new Service();
        Call call = (Call) service.createCall();

        //
        // Prepare the call for the Web service
        //
        call.setTargetEndpointAddress(new java.net.URL(endpoint));
        call.setOperationName("loginCms");
        call.addParameter("request", XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);

        //
        // Make the actual call and assign the answer to a String
        //
        loginTicketResponse = (String) call.invoke(new Object[]{
                Base64.encode(loginTicketRequest_xml_cms)});

        return loginTicketResponse;
    }


    /**
     * Crea el mensaje CMS del tipo “SignedData” que contiene el mensaje
     * el LoginTicketRequest.xml y su firma electrónica utilizando
     * SHA1+RSA. De esta forma, se obtiene el TRA (LoginTicketRequest.xml.cms).
     * <p>
     * CMS: Cryptographic Message Syntax
     *
     * @param p12file
     * @param p12pass
     * @param signer
     * @param dstDN
     * @param service
     * @param ticketTime
     * @return
     */
    private static byte[] crearCMS(String p12file, String p12pass, String signer, String dstDN, String service, Long ticketTime) {

        PrivateKey pKey = null;
        X509Certificate pCertificate = null;
        byte[] asn1_cms = null;
        Store certs = null;
        String loginTicketRequest_xml;
        String signerDN = null;

        //
        // Manage Keys & Certificates
        //
        try {
            // Create a keystore using keys from the pkcs#12 p12file
            InputStream p12stream = new FileInputStream(new File(p12file));

            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(p12stream, p12pass.toCharArray());
            p12stream.close();

            // Get Certificate & Private key from KeyStore
            pKey = (PrivateKey) ks.getKey(signer, p12pass.toCharArray());
            pCertificate = (X509Certificate) ks.getCertificate(signer);
            signerDN = pCertificate.getSubjectDN().toString();

            // Create a list of Certificates to include in the final CMS
            ArrayList<X509Certificate> certList = new ArrayList<X509Certificate>();
            certList.add(pCertificate);

            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            certs = new JcaCertStore(certList);

            //cstore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //
        // Create XML Message
        //
        loginTicketRequest_xml = crearLoginTicketRequest(signerDN, dstDN, service, ticketTime);

        //
        // Create CMS Message
        //
        try {
            // Create a new empty CMS Message
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();

            //cambie SHA1withRSA por DIGEST_SHA1
            ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(pKey);

            // Add a Signer to the Message
            gen.addSignerInfoGenerator(
                    new JcaSignerInfoGeneratorBuilder(
                            new JcaDigestCalculatorProviderBuilder().setProvider("BC").build())
                            .build(sha1Signer, pCertificate));

            // Add the Certificate to the Message
            gen.addCertificates(certs);

            // Add the data (XML) to the Message
            CMSTypedData msg = new CMSProcessableByteArray(loginTicketRequest_xml.getBytes());

            // Add a Sign of the Data to the Message
            CMSSignedData signed = gen.generate(msg, true);

            asn1_cms = signed.getEncoded();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return (asn1_cms);
    }

    /**
     * Crea el mensaje XML (TRA) para el WebService de autenticación y autorización de AFIP (WSAA).
     * TRA: Ticket de requerimiento de acceso.
     * Retorna una cadena con el LoginTicketRequest.xml
     *
     * @param signerDN
     * @param dstDN
     * @param service
     * @param ticketTime
     * @return
     */
    private static String crearLoginTicketRequest(String signerDN, String dstDN, String service, Long ticketTime) {
        String LoginTicketRequest_xml;
        Calendar c = Calendar.getInstance();
        Date ahora = new Date(); //FechaHora actual, al momento de realizar el pedido del ticket de acceso

        //FechaHora de generación del pedido (ticket de acceso)
        c.setTime(ahora);
        String xmlGenTime = DatatypeConverter.printDateTime(c);

        //FechaHora de expiración del token de acceso
        c.setTime(new Date(ahora.getTime() + (ticketTime * 1000)));
        String xmlExpTime = DatatypeConverter.printDateTime(c);

        //Unique Id
        String uniqueId = new Long(ahora.getTime() / 1000).toString();

        LoginTicketRequest_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<loginTicketRequest version=\"1.0\">"
                + "<header>"
                + "<source>" + signerDN + "</source>"
                + "<destination>" + dstDN + "</destination>"
                + "<uniqueId>" + uniqueId + "</uniqueId>"
                + "<generationTime>" + xmlGenTime + "</generationTime>"
                + "<expirationTime>" + xmlExpTime + "</expirationTime>"
                + "</header>"
                + "<service>" + service + "</service>"
                + "</loginTicketRequest>";

        //System.out.println("TRA: " + LoginTicketRequest_xml);

        return (LoginTicketRequest_xml);
    }

    /*
     *  Genero el certificado p12 utilizando la clave privada y el certificado .crt (que obtuve en la
     *  web de AFIP), utilizo un signer y un password.
     */
    private void generarCertificadoP12() {
        logger.info("Configurando certificado");

        crearArchivoClavePrivada();
        crearArchivoCertificadoCrt();

        String pathArchivoClavePrivada = this.gestorPropiedades.getAbsolutePathConfigurationDir() + "clavePrivada.key";
        String pathArchivoCertificadoCrt = this.gestorPropiedades.getAbsolutePathConfigurationDir() + "certificado.crt";
        String pathArchivoCertificadoP12 = this.gestorPropiedades.getAbsolutePathConfigurationDir() + "certificado.p12";

        //Signer y passwrod a utilizar para crear el certificado p12
        String p12Signer = this.gestorPropiedades.getProperty("keystore-signer");
        String p12Password = this.gestorPropiedades.getProperty("keystore-password");

        // Genero el certificado p12
        String command = "openssl pkcs12 -export -inkey " + pathArchivoClavePrivada + " -in " + pathArchivoCertificadoCrt + " -out " + pathArchivoCertificadoP12 + " -name " + p12Signer + " -password pass:" + p12Password;
        Runtime r = Runtime.getRuntime();
        try {
            Process process = r.exec(command, null, new File(this.gestorPropiedades.getAbsolutePathConfigurationDir()));
            // El thread actual espera a que termine la ejecución del comando (subproceso) para continuar.
            int exitVal = process.waitFor();
            if (exitVal != 0) {
                throw new RuntimeException("No se pudo generar el certificado.p12 requerido para solicitar el TA a AFIP.");
            }
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Creo el archivo clavePrivada.key cuyo contenido se encuentra en una variable en el
     * archivo de configuración.
     */
    private void crearArchivoClavePrivada() {
        File fClavePrivada = new File(this.gestorPropiedades.getAbsolutePathConfigurationDir() + "clavePrivada.key");
        if (!fClavePrivada.exists()) {
            String cadenaClavePrivada = this.gestorPropiedades.getProperty("cadenaClavePrivada");
            try {
                FileUtils.writeStringToFile(fClavePrivada, cadenaClavePrivada, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Creo el certificado.crt cuyo contenido se encuentra en una variable en el
     * archivo de configuración.
     */
    private void crearArchivoCertificadoCrt() {
        File fCertificadoCrt = new File(this.gestorPropiedades.getAbsolutePathConfigurationDir() + "certificado.crt");
        if (!fCertificadoCrt.exists()) {
            String cadenaCertificadoCrt = this.gestorPropiedades.getProperty("cadenaCertificadoCrt");
            try {
                FileUtils.writeStringToFile(fCertificadoCrt, cadenaCertificadoCrt, "UTF-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
