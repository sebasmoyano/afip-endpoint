/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mgdtrat.wsafip;

import fev1.dif.afip.gov.ar.AlicIva;
import fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECompConsResponse;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.afip.mgdtrat.wsafip.CAEResult;
import io.slingr.endpoints.afip.mgdtrat.wsafip.GestorAFIP;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author itraverso
 */
public class GestorAFIPTest {

    GestorAFIP gafip;
    GestorDeConfiguracion gestorConfig;

    public GestorAFIPTest() {
       this.gafip = new GestorAFIP();

       this.gestorConfig = GestorDeConfiguracion.getInstance();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of solicitarCAE method, of class GestorAFIP.
     */
    @Test
    public void testSolicitarCAE() throws Exception {
        System.out.println("solicitarCAE");

        FECAEDetRequest compDetRequest = null;
        CAEResult expResult;

        //Prueba de obtención de CAE

        /*
         Comprobante CÓDIGO Según AFIP:
            ID: 1 - Factura A
            ID: 2 - Nota de Débito A
            ID: 3 - Nota de Crédito A
            ID: 6 - Factura B
            ID: 7 - Nota de Débito B
            ID: 8 - Nota de Crédito B
        */
        int tipoDeComprobante = 1; //1: Factura A

        CAEResult caeResult = this.gafip.solicitarCAE(tipoDeComprobante, this.setearComprobanteParaTestear(tipoDeComprobante));

        System.out.println("--------CAE OBTENIDO--------");
        System.out.println("Número de CAE: " + caeResult.getCaeNumero());
        System.out.println("Vencimiento CAE: " + caeResult.getCaeFechaVencimiento());
        System.out.println("Observacines CAE: " + caeResult.getCaeObservaciones());
        System.out.println("Eventos CAE: " + caeResult.getCaeEventos());
        System.out.println("Número de comprobante: " + caeResult.getComprobanteNumero());

        System.out.println("ULTIMO COMPROBANTE AUTORIZADO (FACTURA A): " + gafip.getNumeroUltimoComprobanteAutorizado(1, 1));

        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Instancio un FECAEDetRequest para realizar pruebas.
     *
     * @param comprobanteTipo
     * @return
     */
    private FECAEDetRequest setearComprobanteParaTestear(int comprobanteTipo) {
        FECAEDetRequest compDetRequest = new FECAEDetRequest();

        compDetRequest.setConcepto(1); //ID: 1 - Producto; ID: 2 - Servicios; ID: 3 - Productos y Servicios

        compDetRequest.setDocTipo(80); //Tipo Documento => ID: 80 - CUIT; ID: 96 - DNI; ID: 86 - CUIL
        compDetRequest.setDocNro(27065567275l); //Número documento

        /*
        //Seteamos el número de comprobante - CbteDesde igual a CbteHasta => pedido individual
        long ultimoNumeroComprobante = this.gafip.getNumeroUltimoComprobanteAutorizado(Integer.parseInt(this.gestorConfig.getProperty("puntoDeVenta")), comprobanteTipo);
        long proximoNumeroComprobante = ultimoNumeroComprobante + 1;

        compDetRequest.setCbteDesde(proximoNumeroComprobante);
        compDetRequest.setCbteHasta(proximoNumeroComprobante);
        */

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        compDetRequest.setCbteFch(sdf.format(new Date())); //Fecha del comprobante

        //Importe total del comprobante
        compDetRequest.setImpTotal(new Double(121)); //Monto Total

        //Importe neto no gravado
        compDetRequest.setImpTotConc(0);

        //Importe neto gravado
        compDetRequest.setImpNeto(100);

        //Importe exento
        compDetRequest.setImpOpEx(0);

        //Importe IVA
        compDetRequest.setImpIVA(new Double(21));

        //Suma de los importes del array de tributos
        compDetRequest.setImpTrib(0);

        //Moneda
        compDetRequest.setMonId("PES"); //Moneda => ID: PES - Pesos Argentinos
        compDetRequest.setMonCotiz(1); //Cotización de la moneda. Debe ser igual a 1 cuando se trate de <MonId>=PES

        //Si tiene IVA, seteamos la alicuota IVA
        if(true) { //if(this.getIva() > 0) {
            ArrayOfAlicIva alicIvaArreglo = new ArrayOfAlicIva();

            AlicIva alicIva = new  AlicIva();
            alicIva.setImporte(new Double(21));
            alicIva.setId(5); //ID: 5 - 21%
            alicIva.setBaseImp(new Double(100));

            alicIvaArreglo.getAlicIva().add(alicIva);

            compDetRequest.setIva(alicIvaArreglo);
        }

        return compDetRequest;
    }

    /**
     * Test of getNumeroUltimoComprobanteAutorizado method, of class GestorAFIP.
     */
    //@Test
    public void testGetNumeroUltimoComprobanteAutorizado() {
        System.out.println("getNumeroUltimoComprobanteAutorizado");

        int puntoVenta = 1;
        int compTipoAFIP = 1;

        long result = this.gafip.getNumeroUltimoComprobanteAutorizado(puntoVenta, compTipoAFIP);

        System.out.println("ULTIMO COMPROBANTE AUTORIZADO (FACTURA A): " + result);

        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of consultarCAEDeComprobante method, of class GestorAFIP.
     */
    //@Test
    public void testConsultarCAEDeComprobante() {
        System.out.println("consultarCAEDeComprobante");
        int puntoVenta = 1;
        int compTipo = 1;
        long compNumero = 2L;

        FECompConsResponse result = this.gafip.consultarCAEDeComprobante(puntoVenta, compTipo, compNumero);

        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of reobtenerCAE method, of class GestorAFIP.
     */
    //@Test
    public void testReobtenerCAE() {
        System.out.println("reobtenerCAE");

        int puntoVenta = 1;
        int compTipo = 1;
        long compNumero = 2L;

        CAEResult caeResult = this.gafip.reobtenerCAE(puntoVenta, compTipo, compNumero);

        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of REPOSITORIOS methods, of class GestorAFIP.
     */
    //@Test
    public void testConsultarRespositorios() {
        //Prueba de acceso a distinto métodos (repositorios) del WS de AFIP
        System.out.println("ULTIMO COMPROBANTE AUTORIZADO (FACTURA A): " + gafip.getNumeroUltimoComprobanteAutorizado(1, 1));
        System.out.println("PUNTO DE VENTA DE PRUEBAS MIO: " + gafip.getPuntoDeVenta());

        //gafip.getTablaPuntosDeVenta();
        this.gafip.getTablaTiposCbte();
        this.gafip.getTablaTiposConcepto();
        this.gafip.getTablaTiposDocumentoAFIP();
        this.gafip.getTablaTiposIvaAFIP();
        this.gafip.getTablaTiposMonedas();
        this.gafip.getTablaTiposOpcional();
    }
}
