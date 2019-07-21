package io.slingr.endpoints.afip;

import fev1.dif.afip.gov.ar.AlicIva;
import fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import io.slingr.endpoints.Endpoint;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.afip.mgdtrat.wsafip.CAEResult;
import io.slingr.endpoints.afip.mgdtrat.wsafip.GestorAFIP;
import io.slingr.endpoints.framework.annotations.*;
import io.slingr.endpoints.services.AppLogs;
import io.slingr.endpoints.services.rest.RestMethod;
import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.ws.exchange.FunctionRequest;
import io.slingr.endpoints.ws.exchange.WebServiceRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

@SlingrEndpoint(name = "afip")
public class AfipEndpoint extends Endpoint {

    private static final Logger logger = LoggerFactory.getLogger(AfipEndpoint.class);

    @ApplicationLogger
    private AppLogs appLogger;

    private GestorAFIP gafip;

    @EndpointConfiguration
    private Json configuration;

    public AfipEndpoint() {
    }

    @Override
    public void endpointStarted() {
        logger.info("Endpoint is started");

        gafip = new GestorAFIP();

        // TODO: copiar o generar certificado
        String certificadoContent = configuration.string("certificado");
        try {
            String certificadoPath = GestorDeConfiguracion.PATH_CONFIG_DIR + GestorDeConfiguracion.CERT_FILE;
            FileUtils.writeStringToFile(new File(certificadoPath), certificadoContent, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can not copy certificate", e);
        }

        // TODO: copiar logo

        // inicializar propiedades
        GestorDeConfiguracion.getInstance().setProperty("empresaNombre", configuration.string("empresaNombre"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos1", configuration.string("empresaDireccion"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos2", configuration.string("empresaTelefono"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos3", configuration.string("empresaEmail"));
        GestorDeConfiguracion.getInstance().setProperty("empresaDatos4", configuration.string("empresaSitioWeb"));
        GestorDeConfiguracion.getInstance().setProperty("empresaTipoContribuyente", configuration.string("empresaTipoContribuyente"));
        GestorDeConfiguracion.getInstance().setProperty("empresaCuit", configuration.string("empresaCuit"));
        GestorDeConfiguracion.getInstance().setProperty("empresaIIBB", configuration.string("empresaIngresosBrutos"));
        GestorDeConfiguracion.getInstance().setProperty("empresaEstablecimiento", configuration.string("empresaEstablecimiento"));
        GestorDeConfiguracion.getInstance().setProperty("empresaSede", configuration.string("empresaSede"));
        GestorDeConfiguracion.getInstance().setProperty("empresaInicioActividad", configuration.string("empresaInicioActividad"));

        GestorDeConfiguracion.getInstance().setProperty("CUIT", configuration.string("empresaCuit").replaceAll("-", ""));
        GestorDeConfiguracion.getInstance().setProperty("puntoDeVenta", configuration.string("puntoVenta"));

        GestorDeConfiguracion.getInstance().setProperty("endpointAutenticacion", configuration.string("endpointAutenticacion"));
        GestorDeConfiguracion.getInstance().setProperty("endpointFacturacion", configuration.string("endpointFacturacion"));
        GestorDeConfiguracion.getInstance().setProperty("dstdn", configuration.string("destinoServicio"));
        GestorDeConfiguracion.getInstance().setProperty("keystore-signer", configuration.string("certificadoFirmante"));
        GestorDeConfiguracion.getInstance().setProperty("keystore-password", configuration.string("certificadoPassword"));
    }


    /**
     * Obtiene una nueva autorizacion desde AFIP para los siguientes tipos de comprobantes:
     *             ID: 1 - Factura A
     *             ID: 2 - Nota de Débito A
     *             ID: 3 - Nota de Crédito A
     *             ID: 6 - Factura B
     *             ID: 7 - Nota de Débito B
     *             ID: 8 - Nota de Crédito B
     */
    @EndpointFunction(name = "autorizarComprobante")
    public Json autorizarComprobante(Json params) {
        if (params == null) {
            params = Json.map();
        }

        appLogger.info("Solicitud autorizacion de comprobante [%s]", params);

        int tipoComprobante = params.integer("tipoComprobante");
        FECAEDetRequest pedidoAutorizacion = generarPedidoAutorizacion(params);
        CAEResult caeResult = gafip.solicitarCAE(tipoComprobante, pedidoAutorizacion);

        Json res = Json.map();
        res.set("numeroComprobante", caeResult.getComprobanteNumero());
        res.set("numeroCae", caeResult.getCaeNumero());
        if (caeResult.getCaeFechaVencimiento() != null) {
            res.set("vencimientoCae", new SimpleDateFormat("dd/MM/yyyy").format(caeResult.getCaeFechaVencimiento()));
        }
        if (caeResult.getCaeObservaciones() != null) {
            res.set("observacionesCae", caeResult.getCaeObservaciones());
        }

        appLogger.info("Nueva autorización obtenida [%s]", res);

        return res;
    }

    private FECAEDetRequest generarPedidoAutorizacion(Json payload) {
        FECAEDetRequest compDetRequest = new FECAEDetRequest();
        compDetRequest.setConcepto(payload.integer("concepto", 1)); // ID: 1 - Producto; ID: 2 - Servicios; ID: 3 - Productos y Servicios
        compDetRequest.setDocTipo(payload.integer("tipoDocumento")); // Tipo Documento => ID: 80 - CUIT; ID: 96 - DNI; ID: 86 - CUIL
        compDetRequest.setDocNro(payload.longInteger("numeroDocument")); // Número documento
        compDetRequest.setCbteFch(payload.string("fecha")); // Fecha del comprobante
        // Importe total del comprobante
        compDetRequest.setImpTotal(payload.decimal("total")); // Monto Total
        // Importe neto no gravado
        compDetRequest.setImpTotConc(0);
        // Importe neto gravado
        compDetRequest.setImpNeto(100);
        // Importe exento
        compDetRequest.setImpOpEx(0);
        // Importe IVA
        compDetRequest.setImpIVA(payload.decimal("porcentajeIva"));
        //Suma de los importes del array de tributos
        compDetRequest.setImpTrib(0);
        // Moneda
        compDetRequest.setMonId("PES"); // Moneda => ID: PES - Pesos Argentinos
        compDetRequest.setMonCotiz(1); // Cotización de la moneda. Debe ser igual a 1 cuando se trate de <MonId>=PES
        //Si tiene IVA, seteamos la alicuota IVA
        ArrayOfAlicIva alicIvaArreglo = new ArrayOfAlicIva();
        AlicIva alicIva = new  AlicIva();
        alicIva.setImporte(payload.decimal("porcentajeIva"));
        alicIva.setId(5); // ID: 5 - 21%
        alicIva.setBaseImp(100);
        alicIvaArreglo.getAlicIva().add(alicIva);
        compDetRequest.setIva(alicIvaArreglo);
        return compDetRequest;
    }


}
