/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.app.mgdtrat.generacionPDFs;

import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.Comprador;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalImpresion;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.ComprobanteFiscalRenglon;
import io.slingr.endpoints.afip.mgdtrat.generacionPDFs.GeneradorPdf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author itraverso
 */
public class GeneradorPdfTest {

    public GeneradorPdfTest() {
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
     * Test of impresionComprobanteFiscal method, of class GeneradorPdf.
     */
    //@Test
    public void testImpresionComprobanteFiscal() {
        System.out.println("impresionComprobanteFiscal");

        GeneradorPdf.impresionComprobanteFiscal(this.getComprobanteFiscalImpresionParaTestingVariosRenglonesLargo());

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    private ComprobanteFiscalImpresion getComprobanteFiscalImpresionParaTesting1() {
        ComprobanteFiscalImpresion cfi = new ComprobanteFiscalImpresion();

        cfi.setComprobanteLetra("A");
        cfi.setComprobanteCodigo("1"); //El código de AFIP: según tipo de comprobante y letra.
        /*
        Comprobante CÓDIGO Según AFIP:
            ID: 1 - Factura A
            ID: 2 - Nota de Débito A
            ID: 3 - Nota de Crédito A
            ID: 6 - Factura B
            ID: 7 - Nota de Débito B
            ID: 8 - Nota de Crédito B
        */

        cfi.setComprobanteNumero("1");
        cfi.setComprobanteTipo("Factura"); //Como aparecerá en el pdf
        cfi.setComprobanteFecha(new Date()); //Fecha en que se emite el comprobante.

        Comprador c = new Comprador();
        c.setNombre("Cacho, de Pruebas");
        c.setDomicilioComercial("La lora 417 - Godoy Cruz - Mendoza");
        //c.setCuit("11-11111111-1");
        c.setResponsabilidadIva("Responsable Inscripto");
        cfi.setComprador(c);

        cfi.setCondicionDeVenta("Contado");
        cfi.setObservaciones("Observaciones de prueba");
        cfi.setCaeNumero(11111l);
        cfi.setCaeFechaVencimiento(new Date());
        cfi.setCaeObservaciones("Observación del CAE.");
        cfi.setDescuento(new Double(0)); //El total en pesos que se bonifica en el comprobante.
        cfi.setSubtotal(new Double(100));
        cfi.setIva(new Double(21));
        cfi.setTotal(new Double(121));
        cfi.setNotaAlPie("Nota al pie!");
        cfi.setCodigoDeBarras("272182424510110000269241712335626201906216"); //Si no se especifica lo genera por defecto como lo hace AFIP (Ver ComprobanteFiscalImpresion)

        ComprobanteFiscalRenglon cfr = new ComprobanteFiscalRenglon();
        cfr.setItemCodigo("001111");
        cfr.setItemDescripcion("Producto");
        cfr.setCantidad(new Double(3));
        cfr.setPorcentajeBonificacion(new Double(50.041));
        cfr.setPrecioUnidad(new Double(10));
        cfr.setPrecioTotal(new Double(30));

        List<ComprobanteFiscalRenglon> renglones = new ArrayList<ComprobanteFiscalRenglon>();
        renglones.add(cfr);

        cfi.setRenglones(renglones);

        return cfi;
    }

    private ComprobanteFiscalImpresion getComprobanteFiscalImpresionParaTestingVariosRenglones() {
        ComprobanteFiscalImpresion cfi = new ComprobanteFiscalImpresion();

        cfi.setComprobanteLetra("A");
        cfi.setComprobanteCodigo("1"); //El código de AFIP: según tipo de comprobante y letra.
        /*
        Comprobante CÓDIGO Según AFIP:
            ID: 1 - Factura A
            ID: 2 - Nota de Débito A
            ID: 3 - Nota de Crédito A
            ID: 6 - Factura B
            ID: 7 - Nota de Débito B
            ID: 8 - Nota de Crédito B
        */

        cfi.setComprobanteNumero("1");
        cfi.setComprobanteTipo("Factura"); //Como aparecerá en el pdf
        cfi.setComprobanteFecha(new Date()); //Fecha en que se emite el comprobante.

        Comprador c = new Comprador();
        c.setNombre("Cacho, de Pruebas");
        c.setDomicilioComercial("La lora 417 - Godoy Cruz - Mendoza");
        //c.setCuit("11-11111111-1");
        c.setResponsabilidadIva("Responsable Inscripto");
        cfi.setComprador(c);

        cfi.setCondicionDeVenta("Contado");
        cfi.setObservaciones("Observaciones de prueba");
        cfi.setCaeNumero(11111l);
        cfi.setCaeFechaVencimiento(new Date());
        cfi.setCaeObservaciones("Observación del CAE.");
        cfi.setDescuento(new Double(215.513)); //El total en pesos que se bonifica en el comprobante.
        cfi.setSubtotal(new Double(100));
        cfi.setIva(new Double(21));
        cfi.setTotal(new Double(121));
        cfi.setNotaAlPie("Nota al pie!");
        cfi.setCodigoDeBarras("272182424510110000269241712335626201906216");  //Si no se especifica lo genera por defecto como lo hace AFIP (Ver ComprobanteFiscalImpresion)

        //Renglones
        List<ComprobanteFiscalRenglon> renglones = new ArrayList<ComprobanteFiscalRenglon>();

        for(int i = 0; i < 20; i++) {
            ComprobanteFiscalRenglon cfr = new ComprobanteFiscalRenglon();

            cfr.setItemCodigo("001111");
            cfr.setItemDescripcion("Producto");
            cfr.setCantidad(new Double(3));
            cfr.setPorcentajeBonificacion(new Double(50.041));
            cfr.setPrecioUnidad(new Double(10));
            cfr.setPrecioTotal(new Double(30));

            renglones.add(cfr);
        }

        cfi.setRenglones(renglones);

        return cfi;
    }

    private ComprobanteFiscalImpresion getComprobanteFiscalImpresionParaTestingVariosRenglonesLargo() {
        ComprobanteFiscalImpresion cfi = new ComprobanteFiscalImpresion();

        cfi.setComprobanteLetra("A");
        cfi.setComprobanteCodigo("1"); //El código de AFIP: según tipo de comprobante y letra.
        /*
        Comprobante CÓDIGO Según AFIP:
            ID: 1 - Factura A
            ID: 2 - Nota de Débito A
            ID: 3 - Nota de Crédito A
            ID: 6 - Factura B
            ID: 7 - Nota de Débito B
            ID: 8 - Nota de Crédito B
        */

        cfi.setComprobanteNumero("1");
        cfi.setComprobanteTipo("Factura"); //Como aparecerá en el pdf
        cfi.setComprobanteFecha(new Date()); //Fecha en que se emite el comprobante.

        Comprador c = new Comprador();
        c.setNombre("Cacho, de Pruebas");
        c.setDomicilioComercial("La lora 417 - Godoy Cruz - Mendoza");
        //c.setCuit("11-11111111-1");
        c.setResponsabilidadIva("Responsable Inscripto");
        cfi.setComprador(c);

        cfi.setCondicionDeVenta("Contado");
        cfi.setObservaciones("Observaciones de prueba");
        cfi.setCaeNumero(1234567890123456l);
        cfi.setCaeFechaVencimiento(new Date());
        cfi.setCaeObservaciones("Observación del CAE.");
        cfi.setDescuento(new Double(215.513)); //El total en pesos que se bonifica en el comprobante.
        cfi.setSubtotal(new Double(100000.5055));
        cfi.setIva(new Double(21.0001));
        cfi.setTotal(new Double(100000.5055));
        cfi.setNotaAlPie("Nota al pie!");
        //cfi.setCodigoDeBarras("272182424510110000269241712335626201906216");  //Si no se especifica lo genera por defecto como lo hace AFIP (Ver ComprobanteFiscalImpresion)

        //Renglones
        List<ComprobanteFiscalRenglon> renglones = new ArrayList<ComprobanteFiscalRenglon>();

        for(int i = 0; i < 10; i++) {
            ComprobanteFiscalRenglon cfr = new ComprobanteFiscalRenglon();

            cfr.setItemCodigo("123456789");
            cfr.setItemDescripcion("Este parece la historia de mi vida, pero en realidad es solo la descripción de un renglón de un famoso comprobante que se imprimio por allá en el año 1602.");
            cfr.setCantidad(new Double(1000.0005));
            cfr.setPorcentajeBonificacion(new Double(50.041));
            cfr.setPrecioUnidad(new Double(100000.5055));
            cfr.setPrecioTotal(new Double(100000.5055));

            renglones.add(cfr);
        }

        cfi.setRenglones(renglones);

        return cfi;
    }
}
