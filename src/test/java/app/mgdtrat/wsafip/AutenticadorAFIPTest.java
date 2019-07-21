/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mgdtrat.wsafip;

import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import io.slingr.endpoints.afip.mgdtrat.wsafip.AutenticadorAFIP;
import io.slingr.endpoints.afip.mgdtrat.wsafip.TicketAccesoAFIP;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author itraverso
 */
public class AutenticadorAFIPTest {

    private GestorDeConfiguracion gestorConfig;

    public AutenticadorAFIPTest() {
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
     * Test of autenticacionYAutorizacion method, of class AutenticadorAFIP.
     */
    //@Test
    public void testAutenticacionYAutorizacion() throws Exception {
        System.out.println("autenticacionYAutorizacion");

        //Prueba de autenticación en AFIP, obtención de Ticket de Acceso
        AutenticadorAFIP instance = new AutenticadorAFIP();
        TicketAccesoAFIP taa = instance.getTicketAccesoAFIP();

        //fail("The test case is a prototype.");
    }
}
