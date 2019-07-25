package io.slingr.endpoints.afip;

import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.Comprador;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalImpresion;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalRenglon;
import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.utils.tests.EndpointTests;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Ignore("For dev proposes")
public class AfipEndpointTest {

    private static final Logger logger = LoggerFactory.getLogger(AfipEndpointTest.class);

    private static EndpointTests test;

    @BeforeClass
    public static void init() throws Exception {
        test = EndpointTests.start(new io.slingr.endpoints.afip.Runner(), "test.properties");
    }

    @Test
    public void testAutorizarComprobante() throws Exception {
        // build request
        final Json req = Json.map();
        req.set("tipoComprobante", 1);
        req.set("tipoDocumento", 80);
        req.set("numeroDocumento", "27065567275");
        req.set("fecha", "20190723"); // yyyyMMdd
        req.set("total", 121.00);
        req.set("totalNeto", 100);
        req.set("impuestos", 21);

        // test request
        Json res = test.executeFunction("autorizarComprobante", req);
        logger.info("Autorizacion response: " + res.toString());
    }

    @Test
    public void testImprimirComprobante() throws Exception {
        // build request
        final Json factura = Json.map();
        factura.set("tipo", "A");
        factura.set("codigo", "1");
        factura.set("numero", "1");
        factura.set("tipoLabel", "Factura");
        factura.set("fecha", "20190717");
        factura.set("cae", "1234567890123456");
        factura.set("caeVencimiento", "20200717");
        factura.set("subtotal", 100.0);
        factura.set("iva", 21.0);
        factura.set("total", 121.0);

        final Json comprador = Json.map();
        comprador.set("nombre", "Max Power");
        comprador.set("domicilio", "Chubut 7868, Carrodilla, Mendoza");
        comprador.set("responsabilidadFrenteAlIva", "Responsable Inscripto");
        comprador.set("documentoTipo", "CUIT");
        comprador.set("documentoNumero", "20-30972191-9");
        factura.set("comprador", comprador);

        Json items = Json.list();
        Json item = Json.map();
        item.set("cantidad", 1);
        item.set("producto", "Mesa cocina");
        item.set("codigoProducto", "0001");
        item.set("precio", 100.0);
        item.set("subtotal", 100.0);
        items.push(item);
        factura.set("items", items);

        // test request
        Json res = test.executeFunction("imprimirComprobante", factura);
        logger.info("Imprimir comprobante response: " + res.toString());
    }



}