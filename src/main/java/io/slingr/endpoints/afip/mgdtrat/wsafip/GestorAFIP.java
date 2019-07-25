/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.wsafip;

import io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.*;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.services.datastores.DataStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * @author itraverso
 */
public class GestorAFIP {

    private AutenticadorAFIP autenticadorAFIP;
    private GestorDeConfiguracion gestorConfig;
    private DataStore configStore;

    private static final String COMPROBANTE_APROBADO = "A";
    private static final String COMPROBANTE_RECHAZADO = "R";
    private static final String COMPROBANTE_RECHAZADO_PARCIAL = "P";


    public void inicializar(DataStore configStore) {
        this.configStore = configStore;
        this.gestorConfig = GestorDeConfiguracion.getInstance();

        System.setProperty("http.proxyHost", this.gestorConfig.getProperty("http_proxy", ""));
        System.setProperty("http.proxyPort", this.gestorConfig.getProperty("http_proxy_port", ""));

        System.setProperty("https.proxyHost", this.gestorConfig.getProperty("http_proxy", ""));
        System.setProperty("https.proxyPort", this.gestorConfig.getProperty("http_proxy_port", ""));

        autenticadorAFIP = new AutenticadorAFIP(configStore);
        autenticadorAFIP.inicializar();
    }


    private Object ejecutarOperacionWebService(String req, Class responseClass) {
        try {
            URL wsdlLocation = new URL(this.gestorConfig.getProperty("endpointFacturacion"));
            io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.Service service = new io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.Service(wsdlLocation);

            QName portQName = new QName("http://ar.gov.afip.dif.FEV1/", "ServiceSoap12");

            // Call Web Service Operation
            Dispatch<Source> sourceDispatch = null;
            sourceDispatch = service.createDispatch(portQName, Source.class, Service.Mode.PAYLOAD);
            Source result = sourceDispatch.invoke(new StreamSource(new StringReader(req)));

            JAXBContext context = JAXBContext.newInstance(responseClass);
            Unmarshaller um = context.createUnmarshaller();

            return um.unmarshal(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private String getStringRequestSimple(String nombreOperacion) {
        TicketAccesoAFIP taAFIP = this.autenticadorAFIP.inicializar();
        String cuit = this.gestorConfig.getProperty("CUIT");

        String req = "<" + nombreOperacion + "  xmlns=\"http://ar.gov.afip.dif.FEV1/\"><Auth>" +
                "<Token>" + taAFIP.getToken() + "</Token>" +
                "<Sign>" + taAFIP.getSign() + "</Sign>" +
                "<Cuit>" + cuit + "</Cuit>" +
                "</Auth></" + nombreOperacion + ">";

        return req;
    }

    /**
     * Permite consultar la tabla repositoria de Tipos de Documentos
     * de AFIP.
     */
    public void getTablaTiposDocumentoAFIP() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposDoc"), FEParamGetTiposDocResponse.class);

        FEParamGetTiposDocResponse tiposDoc = (FEParamGetTiposDocResponse) result;

        ArrayOfDocTipo arreglo = tiposDoc.getFEParamGetTiposDocResult().getResultGet();

        System.out.println("-----Tabla TIPOS DOCUMENTOS AFIP-----");

        for (DocTipo tipoDoc : arreglo.getDocTipo()) {
            System.out.println("ID: " + tipoDoc.getId());
            System.out.println("Descripcion: " + tipoDoc.getDesc());
            System.out.println("Fecha desde: " + tipoDoc.getFchDesde());
            System.out.println("Fecha hasta: " + tipoDoc.getFchHasta());
        }
    }

    /**
     * Permite consultar la tabla repositoria de Tipos de IVA
     * de AFIP.
     */
    public void getTablaTiposIvaAFIP() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposIva"), FEParamGetTiposIvaResponse.class);

        FEParamGetTiposIvaResponse tiposIva = (FEParamGetTiposIvaResponse) result;

        ArrayOfIvaTipo arreglo = tiposIva.getFEParamGetTiposIvaResult().getResultGet();

        System.out.println("-----Tabla TIPOS IVA AFIP-----");

        for (IvaTipo tipoIva : arreglo.getIvaTipo()) {
            System.out.println("ID: " + tipoIva.getId());
            System.out.println("Descripcion: " + tipoIva.getDesc());
            System.out.println("Fecha desde: " + tipoIva.getFchDesde());
            System.out.println("Fecha hasta: " + tipoIva.getFchHasta());
        }
    }

    /**
     * Permite consultar la tabla repositoria de Tipos de Comprobantes
     * de AFIP.
     */
    public void getTablaTiposCbte() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposCbte"), FEParamGetTiposCbteResponse.class);

        FEParamGetTiposCbteResponse tiposCbte = (FEParamGetTiposCbteResponse) result;

        ArrayOfCbteTipo arreglo = tiposCbte.getFEParamGetTiposCbteResult().getResultGet();

        System.out.println("-----Tabla TIPOS COMPROBANTES AFIP-----");

        for (CbteTipo tipoCbte : arreglo.getCbteTipo()) {
            System.out.println("ID: " + tipoCbte.getId());
            System.out.println("Descripcion: " + tipoCbte.getDesc());
            System.out.println("Fecha desde: " + tipoCbte.getFchDesde());
            System.out.println("Fecha hasta: " + tipoCbte.getFchHasta());
        }
    }

    /**
     * Permite consultar la tabla repositoria de Tipos de Conceptos
     * de AFIP.
     */
    public void getTablaTiposConcepto() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposConcepto"), FEParamGetTiposConceptoResponse.class);

        FEParamGetTiposConceptoResponse tiposConcepto = (FEParamGetTiposConceptoResponse) result;

        ArrayOfConceptoTipo arreglo = tiposConcepto.getFEParamGetTiposConceptoResult().getResultGet();

        System.out.println("-----Tabla TIPOS CONCEPTOS AFIP-----");

        for (ConceptoTipo tipoConcepto : arreglo.getConceptoTipo()) {
            System.out.println("ID: " + tipoConcepto.getId());
            System.out.println("Descripcion: " + tipoConcepto.getDesc());
            System.out.println("Fecha desde: " + tipoConcepto.getFchDesde());
            System.out.println("Fecha hasta: " + tipoConcepto.getFchHasta());
        }
    }

    /**
     * Permite consultar la tabla repositoria de Tipos de Monedas
     * de AFIP.
     */
    public void getTablaTiposMonedas() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposMonedas"), FEParamGetTiposMonedasResponse.class);

        FEParamGetTiposMonedasResponse tiposMoneda = (FEParamGetTiposMonedasResponse) result;

        ArrayOfMoneda arreglo = tiposMoneda.getFEParamGetTiposMonedasResult().getResultGet();

        System.out.println("-----Tabla TIPOS MONEDAS AFIP-----");

        for (Moneda tipoMoneda : arreglo.getMoneda()) {
            System.out.println("ID: " + tipoMoneda.getId());
            System.out.println("Descripcion: " + tipoMoneda.getDesc());
            System.out.println("Fecha desde: " + tipoMoneda.getFchDesde());
            System.out.println("Fecha hasta: " + tipoMoneda.getFchHasta());
        }
    }

    /**
     * Permite consultar la tabla repositoria de Tipos Opcional
     * de AFIP.
     */
    public void getTablaTiposOpcional() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetTiposOpcional"), FEParamGetTiposOpcionalResponse.class);

        FEParamGetTiposOpcionalResponse tiposOpcional = (FEParamGetTiposOpcionalResponse) result;

        ArrayOfErr errores = tiposOpcional.getFEParamGetTiposOpcionalResult().getErrors();

        if (errores != null) {
            throw new RuntimeException(this.getCadenaErrores(errores));
        } else {
            ArrayOfOpcionalTipo arreglo = tiposOpcional.getFEParamGetTiposOpcionalResult().getResultGet();

            System.out.println("-----Tabla TIPOS OPCIONAL AFIP-----");

            for (OpcionalTipo opcionalTipo : arreglo.getOpcionalTipo()) {
                System.out.println(opcionalTipo.getDesc());
            }
        }
    }

    /**
     * Permite consultar la tabla repositoria de Puntos de Ventas
     * de AFIP.
     */
    public void getTablaPuntosDeVenta() {
        Object result = this.ejecutarOperacionWebService(getStringRequestSimple("FEParamGetPtosVenta"), FEParamGetPtosVentaResponse.class);

        FEParamGetPtosVentaResponse ptosVenta = (FEParamGetPtosVentaResponse) result;

        ArrayOfErr errores = ptosVenta.getFEParamGetPtosVentaResult().getErrors();

        if (errores != null) {
            throw new RuntimeException(this.getCadenaErrores(errores));

        } else {
            ArrayOfPtoVenta arreglo = ptosVenta.getFEParamGetPtosVentaResult().getResultGet();

            System.out.println("-----Tabla PUNTOS DE VENTA-----");

            for (PtoVenta ptoVenta : arreglo.getPtoVenta()) {
                System.out.println("Número: " + ptoVenta.getNro());
                System.out.println("Bloqueado: " + ptoVenta.getBloqueado());
                System.out.println("Emisión tipo: " + ptoVenta.getEmisionTipo());
                System.out.println("Fecha baja: " + ptoVenta.getFchBaja());
            }
        }
    }

    /**
     * Método para solictar el CAE de AFIP.
     *
     * @param tipoComprobante
     * @param pedidoAutorizacion
     * @return // Si hay observaciones las retornamos.
     */
    public CAEResult solicitarCAE(int tipoComprobante, FECAEDetRequest pedidoAutorizacion) {
        CAEResult caeResult = new CAEResult();
        FECAERequest compRequest = new FECAERequest();
        FECAECabRequest compCabRequest = new FECAECabRequest();
        ArrayOfFECAEDetRequest compDetRequestArray = new ArrayOfFECAEDetRequest();

        //Seteamos el número de comprobante - CbteDesde igual a CbteHasta => pedido individual
        if (pedidoAutorizacion.getCbteDesde() <= 0l) {
            long ultimoNumeroComprobante = this.getNumeroUltimoComprobanteAutorizado(Integer.parseInt(this.gestorConfig.getProperty("puntoDeVenta")), tipoComprobante);
            long proximoNumeroComprobante = ultimoNumeroComprobante + 1;

            pedidoAutorizacion.setCbteDesde(proximoNumeroComprobante);
            pedidoAutorizacion.setCbteHasta(proximoNumeroComprobante);
        }

        //Datos de la cabera de la solicitud
        compCabRequest.setCbteTipo(tipoComprobante);
        compCabRequest.setPtoVta(this.getPuntoDeVenta());
        compCabRequest.setCantReg(1);

        //Datos del Detalle de la solicitud (Datos de la cabecera del comprobante)
        compDetRequestArray.getFECAEDetRequest().add(pedidoAutorizacion);

        compRequest.setFeCabReq(compCabRequest);
        compRequest.setFeDetReq(compDetRequestArray);


        //Creamos el objeto request (la solicitud)
        FECAESolicitar req = new FECAESolicitar();

        req.setFeCAEReq(compRequest);
        req.setAuth(this.getObjetoFEAuthRequest());

        //Convertimos el objeto compRequest a un string para armar el request
        String reqXML = getXMLFromObject(req, FECAESolicitar.class);

        Object result = this.ejecutarOperacionWebService(reqXML, FECAESolicitarResponse.class);

        FECAESolicitarResponse respuestaCAE = (FECAESolicitarResponse) result;

        //Eventos
        String cadenaEventos = this.getCadenaEventos(respuestaCAE.getFECAESolicitarResult().getEvents());

        //Errores
        ArrayOfErr errores = respuestaCAE.getFECAESolicitarResult().getErrors();

        if (errores != null) {
            String cadenaErrores = this.getCadenaErrores(errores);

            if (cadenaErrores.contains("10016")) {
                long ultimoCompAuth = this.getNumeroUltimoComprobanteAutorizado(this.getPuntoDeVenta(), tipoComprobante);

                cadenaErrores += " El ultimo número de comprobante autorizado (con CAE) para este tipo de comprobante fue el " + ultimoCompAuth + ".";
            }

            throw new RuntimeException(cadenaErrores + cadenaEventos);

        } else {
            FECAECabResponse caeCabResponse = respuestaCAE.getFECAESolicitarResult().getFeCabResp();
            ArrayOfFECAEDetResponse arregloCAEDetResponse = respuestaCAE.getFECAESolicitarResult().getFeDetResp();

            FECAEDetResponse caeDetResponse = arregloCAEDetResponse.getFECAEDetResponse().get(0);

            //-----Observaciones
            String cadenaObsercaciones = "";
            ArrayOfObs obs = caeDetResponse.getObservaciones();

            if (obs != null) {
                cadenaObsercaciones = this.getCadenaObservaciones(obs);

                if (cadenaObsercaciones.contains("10016")) {
                    long ultimoCompAuth = this.getNumeroUltimoComprobanteAutorizado(this.getPuntoDeVenta(), tipoComprobante);

                    cadenaObsercaciones += " El ultimo número de comprobante autorizado (con CAE) para este tipo de comprobante fue el " + ultimoCompAuth + ".";
                }
            }

            //Si está el número de CAE de AFIP lo seteamos al comprobante.
            if (caeDetResponse.getResultado().equals(this.COMPROBANTE_APROBADO) &&
                    caeDetResponse.getCAE() != null && !caeDetResponse.getCAE().equals("")) {

                /*
                System.out.println("--------CAE OBTENIDO--------");
                System.out.println("CAE: " + caeDetResponse.getCAE());
                System.out.println("Vencimiento CAE: " + caeDetResponse.getCAEFchVto());
                System.out.println("Observacines CAE: " + cadenaObsercaciones);
                */

                caeResult.setComprobanteNumero(pedidoAutorizacion.getCbteDesde());
                caeResult.setCaeNumero(Long.parseLong(caeDetResponse.getCAE()));

                try {
                    caeResult.setCaeFechaVencimiento(new SimpleDateFormat("yyyyMMdd").parse(caeDetResponse.getCAEFchVto()));
                } catch (ParseException pe) {
                    throw new RuntimeException(pe.getMessage());
                }

                caeResult.setCaeObservaciones(cadenaObsercaciones);
                caeResult.setCaeEventos(cadenaEventos);

            } else {
                throw new RuntimeException(cadenaEventos + cadenaObsercaciones + " El número de CAE de la AFIP viene vacio.");
            }
        }

        return caeResult;
    }

    /**
     * Creamos el objeto FEAuthRequest con la información del ticket
     * de acceso de la AFIP (obtenido previamente y almacenado en la
     * base de datos).
     *
     * @return
     */
    private FEAuthRequest getObjetoFEAuthRequest() {
        //Creamos el objeto autorización.
        TicketAccesoAFIP taAFIP = this.autenticadorAFIP.inicializar();
        String cuit = this.gestorConfig.getProperty("CUIT");

        FEAuthRequest authRequest = new FEAuthRequest();

        authRequest.setToken(taAFIP.getToken());
        authRequest.setSign(taAFIP.getSign());
        authRequest.setCuit(Long.parseLong(cuit));

        return authRequest;
    }

    /**
     * Convertimos un objeto request a un string para armar el request (la solicitud)
     *
     * @param req
     * @param requestClass
     * @return
     */
    private String getXMLFromObject(Object req, Class requestClass) {
        String reqXML = "";

        try {
            JAXBContext context = JAXBContext.newInstance(requestClass);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            m.marshal(req, writer);

            reqXML = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return reqXML;
    }

    /**
     * Convierte un arreglo de errores en una cadena.
     *
     * @param errores
     * @return
     */
    private String getCadenaErrores(ArrayOfErr errores) {
        String cadenaErrores = "";

        if (errores != null) {
            System.out.println("OCURRIERON ERRORES AL CONSULTAR WEBSERVICE AFIP");

            for (Err error : errores.getErr()) {
                if (error != null) {
                    String cadenaEr = "Error código: " + error.getCode();

                    if (error.getMsg() != null && !error.getMsg().equals("")) {
                        cadenaEr += " - Mensaje: " + error.getMsg();
                    }

                    System.out.println(cadenaEr);
                    cadenaErrores += cadenaEr;
                }
            }
        }

        return cadenaErrores;
    }

    /**
     * Convierte un arreglo de observaciones en una cadena.
     *
     * @param observaciones
     * @return
     */
    private String getCadenaObservaciones(ArrayOfObs observaciones) {
        String cadenaObservaciones = "";

        if (observaciones != null) {
            System.out.println("HAY OBSERVACIONES AL CONSULTAR WEBSERVICE AFIP");

            for (Obs obs : observaciones.getObs()) {
                if (obs != null) {
                    String cadenaObs = "Observación código: " + obs.getCode();

                    if (obs.getMsg() != null && !obs.getMsg().equals("")) {
                        cadenaObs += " - Mensaje: " + obs.getMsg();
                    }

                    System.out.println(cadenaObs);
                    cadenaObservaciones += cadenaObs + " ### ";
                }
            }
        }

        return cadenaObservaciones;
    }

    /**
     * Convierte un arreglo de observaciones en una cadena.
     *
     * @param eventos
     * @return
     */
    private String getCadenaEventos(ArrayOfEvt eventos) {
        String cadenaEventos = "";

        if (eventos != null) {
            System.out.println("HAY EVENTOS AL CONSULTAR WEBSERVICE AFIP");

            for (Evt evt : eventos.getEvt()) {
                if (evt != null) {
                    String cadenaEvt = "Evento código: " + evt.getCode();

                    if (evt.getMsg() != null && !evt.getMsg().equals("")) {
                        cadenaEvt += " - Mensaje: " + evt.getMsg();
                    }

                    System.out.println(cadenaEvt);
                    cadenaEventos += cadenaEvt;
                }
            }
        }

        return cadenaEventos;
    }

    /**
     * Retorna el último número de comprobante autorizado (depente del tipo de comprobante y
     * el punto de venta).
     *
     * @param puntoVenta
     * @param compTipoAFIP
     * @return
     */
    public long getNumeroUltimoComprobanteAutorizado(int puntoVenta, int compTipoAFIP) {
        FECompUltimoAutorizado compUA = new FECompUltimoAutorizado();

        compUA.setAuth(this.getObjetoFEAuthRequest());
        compUA.setPtoVta(puntoVenta);
        compUA.setCbteTipo(compTipoAFIP);

        String reqXML = this.getXMLFromObject(compUA, FECompUltimoAutorizado.class);

        Object result = this.ejecutarOperacionWebService(reqXML, FECompUltimoAutorizadoResponse.class);

        FECompUltimoAutorizadoResponse compUAResponse = (FECompUltimoAutorizadoResponse) result;

        ArrayOfErr errores = compUAResponse.getFECompUltimoAutorizadoResult().getErrors();

        if (errores != null) {
            throw new RuntimeException(this.getCadenaErrores(errores));
        }

        return compUAResponse.getFECompUltimoAutorizadoResult().getCbteNro();
    }

    /**
     * Permite consultar el CAE de un comprobante.
     *
     * @param puntoVenta
     * @param compTipo
     * @param compNumero
     * @return
     */
    public FECompConsResponse consultarCAEDeComprobante(int puntoVenta, int compTipo, long compNumero) {
        FECompConsultar feCompConsultar = new FECompConsultar();
        FECompConsultaReq consultaReq = new FECompConsultaReq();

        consultaReq.setCbteNro(compNumero);
        consultaReq.setPtoVta(puntoVenta);
        consultaReq.setCbteTipo(compTipo);

        feCompConsultar.setAuth(this.getObjetoFEAuthRequest());
        feCompConsultar.setFeCompConsReq(consultaReq);

        String reqXML = this.getXMLFromObject(feCompConsultar, FECompConsultar.class);

        Object result = this.ejecutarOperacionWebService(reqXML, FECompConsultarResponse.class);

        FECompConsultarResponse compConsultarResponse = (FECompConsultarResponse) result;

        ArrayOfErr errores = compConsultarResponse.getFECompConsultarResult().getErrors();

        if (errores != null) {
            throw new RuntimeException(this.getCadenaErrores(errores));
        }

        return compConsultarResponse.getFECompConsultarResult().getResultGet();
    }

    /**
     * Permite reobtener el CAE de un comprobante (consulta el CAE obtenido
     * previamente).
     */
    public CAEResult reobtenerCAE(int puntoVenta, int compTipo, long compNumero) {
        String mensajes = "";
        CAEResult caeResult = new CAEResult();
        FECompConsultar feCompConsultar = new FECompConsultar();
        FECompConsultaReq consultaReq = new FECompConsultaReq();

        consultaReq.setCbteNro(compNumero);
        consultaReq.setPtoVta(puntoVenta);
        consultaReq.setCbteTipo(compTipo);

        feCompConsultar.setAuth(this.getObjetoFEAuthRequest());
        feCompConsultar.setFeCompConsReq(consultaReq);

        String reqXML = this.getXMLFromObject(feCompConsultar, FECompConsultar.class);

        Object result = this.ejecutarOperacionWebService(reqXML, FECompConsultarResponse.class);

        FECompConsultarResponse compConsultarResponse = (FECompConsultarResponse) result;

        ArrayOfErr errores = compConsultarResponse.getFECompConsultarResult().getErrors();

        if (errores != null) {
            throw new RuntimeException(this.getCadenaErrores(errores));
        }

        FECompConsResponse caeResultGet = compConsultarResponse.getFECompConsultarResult().getResultGet();

        //-----Observaciones
        String cadenaObsercaciones = "";
        ArrayOfObs obs = caeResultGet.getObservaciones();

        if (obs != null) {
            cadenaObsercaciones = this.getCadenaObservaciones(obs);
            mensajes += cadenaObsercaciones;
        }

        //Si está el número de CAE de AFIP lo seteamos al comprobante.
        if (caeResultGet.getResultado().equals(this.COMPROBANTE_APROBADO) &&
                caeResultGet.getResultado() != null && !caeResultGet.getResultado().equals("")) {

            caeResult.setComprobanteNumero(compNumero);
            caeResult.setCaeNumero(Long.parseLong(caeResultGet.getCodAutorizacion()));

            try {
                caeResult.setCaeFechaVencimiento(new SimpleDateFormat("yyyyMMdd").parse(caeResultGet.getFchVto()));
            } catch (ParseException pe) {
                throw new RuntimeException(pe.getMessage());
            }

            caeResult.setCaeObservaciones(cadenaObsercaciones);

        } else {
            throw new RuntimeException("El número de CAE de la AFIP viene vacio.");
        }

        return caeResult;
    }

    /**
     * Retorna el punto de venta, el cual está seteado en el archivo de
     * configuración
     *
     * @return
     */
    public int getPuntoDeVenta() {
        return Integer.parseInt(this.gestorConfig.getProperty("puntoDeVenta"));
    }
}
