package io.slingr.endpoints.afip;

import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.utils.tests.EndpointTests;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore("For dev proposes")
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
        req.set("puntoVenta", 1);
        req.set("tipoComprobante", 1);
        req.set("tipoDocumento", 80);
        req.set("numeroDocumento", "27065567275");
        req.set("fecha", "20190723"); // yyyyMMdd
        req.set("total", 157.3);
        req.set("totalNeto", 130);
        req.set("iva", 27.3);

        // test request
        Json res = test.executeFunction("_autorizarComprobante", req);
        logger.info("Autorizacion response: " + res.toString());
    }

    @Test
    public void testImprimirComprobante() throws Exception {
        // build request
        final Json factura = Json.map();
        factura.set("puntoVenta", 1);
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
        Json res = test.executeFunction("_imprimirComprobante", factura);
        logger.info("Imprimir comprobante response: " + res.toString());
    }

    @Test
    public void testImprimirPresupuesto() throws Exception {
        // build request
        final Json presupuesto = Json.map();
        presupuesto.set("numero", "1");
        presupuesto.set("tipoLabel", "Presupuesto");
        presupuesto.set("fecha", "20190717");
        presupuesto.set("subtotal", 100.0);
        presupuesto.set("iva", 0);
        presupuesto.set("total", 100.0);

        final Json comprador = Json.map();
        comprador.set("nombre", "Max Power");
        comprador.set("domicilio", "Chubut 7868, Carrodilla, Mendoza");
        comprador.set("responsabilidadFrenteAlIva", "Responsable Inscripto");
        comprador.set("documentoTipo", "CUIT");
        comprador.set("documentoNumero", "20-30972191-9");
        presupuesto.set("comprador", comprador);

        Json items = Json.list();
        Json item = Json.map();
        item.set("cantidad", 1);
        item.set("producto", "Mesa cocina");
        item.set("codigoProducto", "0001");
        item.set("precio", 100.0);
        item.set("subtotal", 100.0);
        items.push(item);
        presupuesto.set("items", items);

        // test request
        Json res = test.executeFunction("_imprimirComprobante", presupuesto);
        logger.info("Imprimir comprobante response: " + res.toString());
    }



}