package io.slingr.endpoints.afip;

import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.utils.tests.EndpointTests;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}