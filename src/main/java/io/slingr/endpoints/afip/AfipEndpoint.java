package io.slingr.endpoints.afip;

import io.slingr.endpoints.Endpoint;
import io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.AlicIva;
import io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import io.slingr.endpoints.afip.fev1.dif.afip.gov.ar.FECAEDetRequest;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.Comprador;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalImpresion;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalRenglon;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.GeneradorPdf;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.afip.mgdtrat.wsafip.CAEResult;
import io.slingr.endpoints.afip.mgdtrat.wsafip.GestorAFIP;
import io.slingr.endpoints.framework.annotations.*;
import io.slingr.endpoints.services.AppLogs;
import io.slingr.endpoints.services.datastores.DataStore;
import io.slingr.endpoints.utils.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SlingrEndpoint(name = "afip", functionPrefix = "_")
public class AfipEndpoint extends Endpoint {

    private static final Logger logger = LoggerFactory.getLogger(AfipEndpoint.class);

    @ApplicationLogger
    private AppLogs appLogger;

    @EndpointDataStore(name = "config")
    private DataStore configStore;

    private GestorAFIP gafip;

    @EndpointConfiguration
    private Json configuration;

    public AfipEndpoint() {
    }

    @Override
    public void endpointStarted() {
        logger.info("Endpoint is started");

        // set timezone

        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

        // inicializar propiedades
        GestorDeConfiguracion.getInstance().setProperty("empresaNombre", configuration.string("empresaNombre"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos1", configuration.string("empresaDireccion"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos2", configuration.string("empresaTelefono", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos3", configuration.string("empresaEmail", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos4", configuration.string("empresaSitioWeb", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaTipoContribuyente", configuration.string("empresaTipoContribuyente"));
        GestorDeConfiguracion.getInstance().setProperty("empresaCuit", configuration.string("empresaCuit"));
        GestorDeConfiguracion.getInstance().setProperty("empresaIIBB", configuration.string("empresaIngresosBrutos", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaEstablecimiento", configuration.string("empresaEstablecimiento", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaSede", configuration.string("empresaSede", ""));
        GestorDeConfiguracion.getInstance().setProperty("empresaInicioActividad", configuration.string("empresaInicioActividad", ""));

        GestorDeConfiguracion.getInstance().setProperty("CUIT", configuration.string("empresaCuit").replaceAll("-", ""));
        GestorDeConfiguracion.getInstance().setProperty("puntoDeVenta", configuration.string("puntoVenta"));

        GestorDeConfiguracion.getInstance().setProperty("endpointAutenticacion", configuration.string("endpointAutenticacion"));
        GestorDeConfiguracion.getInstance().setProperty("endpointFacturacion", configuration.string("endpointFacturacion"));
        GestorDeConfiguracion.getInstance().setProperty("dstdn", configuration.string("destinoServicio"));

        // if the user puts the private key with \n we need to convert it accordingly
        String clavePrivada = configuration.string("clavePrivada").replaceAll("\\\\n", "\n");
        GestorDeConfiguracion.getInstance().setProperty("cadenaClavePrivada", clavePrivada);
        String certificado = configuration.string("certificado").replaceAll("\\\\n", "\n");
        GestorDeConfiguracion.getInstance().setProperty("cadenaCertificadoCrt", certificado);

        gafip = new GestorAFIP();
        gafip.inicializar(configStore);


        // TODO: copiar logo
    }


    /**
     * Obtiene una nueva autorizacion desde AFIP para los siguientes tipos de comprobantes:
     * ID: 1 - Factura A
     * ID: 2 - Nota de Débito A
     * ID: 3 - Nota de Crédito A
     * ID: 6 - Factura B
     * ID: 7 - Nota de Débito B
     * ID: 8 - Nota de Crédito B
     */
    @EndpointFunction(name = "_autorizarComprobante")
    public Json autorizarComprobante(Json params) {
        if (params == null) {
            params = Json.map();
        }

        appLogger.info(String.format("Solicitud autorizacion de comprobante [%s]", params));

        int tipoComprobante = params.integer("tipoComprobante");
        FECAEDetRequest pedidoAutorizacion = generarPedidoAutorizacion(params);
        CAEResult caeResult = gafip.solicitarCAE(tipoComprobante, pedidoAutorizacion);

        Json res = Json.map();
        res.set("numeroComprobante", caeResult.getComprobanteNumero() + "");
        res.set("numeroCae", caeResult.getCaeNumero() + "");
        if (caeResult.getCaeFechaVencimiento() != null) {
            res.set("vencimientoCae", new SimpleDateFormat("dd/MM/yyyy").format(caeResult.getCaeFechaVencimiento()));
        }
        if (caeResult.getCaeObservaciones() != null) {
            res.set("observacionesCae", caeResult.getCaeObservaciones());
        }

        appLogger.info(String.format("Nueva autorización obtenida [%s]", res));

        return res;
    }

    @EndpointFunction(name = "_imprimirComprobante")
    public Json imprimirComprobante(Json comprobanteJson) {
        appLogger.info("Solicitud exportar comprobante a PDF [%s]", comprobanteJson);

        ComprobanteFiscalImpresion comprobanteFiscalImpresion = generarComprobanteImpresion(comprobanteJson);
        String comprobantePath  = GeneradorPdf.impresionComprobanteFiscal(comprobanteFiscalImpresion);

        InputStream is = null;
        try {
            is = new FileInputStream(comprobantePath);
            return files().upload("Comprobante_" + comprobanteJson.string("numero") + ".pdf", is, "application/pdf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private FECAEDetRequest generarPedidoAutorizacion(Json payload) {
        FECAEDetRequest compDetRequest = new FECAEDetRequest();
        compDetRequest.setConcepto(payload.integer("concepto", 1)); // ID: 1 - Producto; ID: 2 - Servicios; ID: 3 - Productos y Servicios
        if (!payload.isEmpty("tipoDocumento")) {
            compDetRequest.setDocTipo(payload.integer("tipoDocumento")); // Tipo Documento => ID: 80 - CUIT; ID: 96 - DNI; ID: 86 - CUIL
        }
        if (!payload.isEmpty("numeroDocumento")) {
            String numeroDocumento = payload.string("numeroDocumento").replaceAll("-", "");
            compDetRequest.setDocNro(Long.parseLong(numeroDocumento)); // Número documento
        }
        compDetRequest.setCbteFch(payload.string("fecha")); // Fecha del comprobante
        // Importe total del comprobante
        compDetRequest.setImpTotal(payload.decimal("total")); // Monto Total
        // Importe neto no gravado
        compDetRequest.setImpTotConc(0);
        // Importe neto gravado
        compDetRequest.setImpNeto(payload.decimal("totalNeto"));
        // Importe exento
        compDetRequest.setImpOpEx(0);
        // Importe IVA
        compDetRequest.setImpIVA(payload.decimal("iva"));
        //Suma de los importes del array de tributos
        compDetRequest.setImpTrib(0);
        // Moneda
        compDetRequest.setMonId("PES"); // Moneda => ID: PES - Pesos Argentinos
        compDetRequest.setMonCotiz(1); // Cotización de la moneda. Debe ser igual a 1 cuando se trate de <MonId>=PES
        //Si tiene IVA, seteamos la alicuota IVA
        ArrayOfAlicIva alicIvaArreglo = new ArrayOfAlicIva();
        AlicIva alicIva = new AlicIva();
        alicIva.setImporte(payload.decimal("iva"));
        alicIva.setId(5); // ID: 5 - 21%
        alicIva.setBaseImp(payload.decimal("totalNeto"));
        alicIvaArreglo.getAlicIva().add(alicIva);
        compDetRequest.setIva(alicIvaArreglo);
        return compDetRequest;
    }

    private ComprobanteFiscalImpresion generarComprobanteImpresion(Json comprobanteJson) {
        ComprobanteFiscalImpresion cfi = new ComprobanteFiscalImpresion();
        cfi.setComprobanteLetra(comprobanteJson.string("tipo"));
        cfi.setComprobanteCodigo(comprobanteJson.string("codigo")); // ID: 1 - Factura A ID: 2 - Nota de Débito A ID: 3 - Nota de Crédito A ID: 6 - Factura B ID: 7 - Nota de Débito B ID: 8 - Nota de Crédito B
        cfi.setComprobanteNumero(comprobanteJson.string("numero"));
        cfi.setComprobanteTipo(comprobanteJson.string("tipoLabel")); // Como aparecerá en el pdf
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            cfi.setComprobanteFecha(sdf.parse(comprobanteJson.string("fecha"))); // Fecha del comprobante
        } catch (ParseException e) {
            logger.warn("No se pudo parsear fecha", e);
        }
        cfi.setCondicionDeVenta(comprobanteJson.string("condicionVenta", "Contado"));
        cfi.setObservaciones("");
        cfi.setCaeNumero(Long.parseLong(comprobanteJson.string("cae")));
        cfi.setCaeFechaVencimiento(new Date());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cfi.setCaeFechaVencimiento(sdf.parse(comprobanteJson.string("caeVencimiento"))); // Fecha del comprobante
        } catch (ParseException e) {
            logger.warn("No se pudo parsear fecha", e);
        }
        cfi.setDescuento(comprobanteJson.decimal("totalDescuento", 0.0)); // El total en pesos que se bonifica en el comprobante.
        cfi.setSubtotal(comprobanteJson.decimal("subtotal"));
        cfi.setIva(comprobanteJson.decimal("iva"));
        cfi.setTotal(comprobanteJson.decimal("total"));

        Json compradorJson = comprobanteJson.json("comprador");
        Comprador c = new Comprador();
        c.setNombre(compradorJson.string("nombre"));
        c.setDomicilioComercial(compradorJson.string("domicilio", ""));
        c.setDocumentoTipo(compradorJson.string("documentoTipo", ""));
        c.setDocumentoNumero(compradorJson.string("documentoNumero", ""));
        c.setResponsabilidadIva(compradorJson.string("responsabilidadFrenteAlIva"));
        cfi.setComprador(c);

        // Renglones
        List<ComprobanteFiscalRenglon> renglones = new ArrayList<ComprobanteFiscalRenglon>();
        List<Json> items = comprobanteJson.jsons("items");
        if (items != null) {
            for (Json item : items) {
                ComprobanteFiscalRenglon cfr = new ComprobanteFiscalRenglon();
                cfr.setCantidad(item.decimal("cantidad"));
                cfr.setItemDescripcion(item.string("producto"));
                cfr.setItemCodigo(item.string("codigoProducto", ""));
                cfr.setPorcentajeBonificacion(item.decimal("bonificacion", 0.0));
                cfr.setPrecioUnidad(item.decimal("precio"));
                cfr.setPrecioTotal(item.decimal("subtotal"));
                renglones.add(cfr);
            }
        }
        cfi.setRenglones(renglones);
        return cfi;
    }


}
