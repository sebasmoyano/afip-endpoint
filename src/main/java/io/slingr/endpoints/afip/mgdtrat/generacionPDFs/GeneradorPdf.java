/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.generacionPDFs;

import com.lowagie.text.pdf.Barcode128;
import io.slingr.endpoints.afip.mgdtrat.util.Formateador;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

/**
 * @author itraverso
 */
public class GeneradorPdf {

    /*
     * @Param nombreJRXML String
     * @param nombreArchivoSalida
     * @return void
     * */
    private static String generarReporte(String nombreJRXML, String nombreArchivoSalida, HashMap<String, Object> params) {
        String tempDir = System.getProperty("java.io.tmpdir");
        String pathRArchivoSalida = tempDir + "/" + nombreArchivoSalida; // Path relativo al archivo
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            System.out.println("Nombre reporte: " + nombreJRXML);
            InputStream pathRFileJRXML = cl.getResource("comprobantesPlantillas/" + nombreJRXML).openStream();

            JasperDesign design = JRXmlLoader.load(pathRFileJRXML);
            JasperReport report = JasperCompileManager.compileReport(design);

            //Original
            params.put("tipoCopia", "ORIGINAL");
            JasperPrint print = JasperFillManager.fillReport(report, params);

            //Duplicado
            params.put("tipoCopia", "DUPLICADO");
            JasperPrint print2 = JasperFillManager.fillReport(report, params);

            //Triplicado
            params.put("tipoCopia", "TRIPLICADO");
            JasperPrint print3 = JasperFillManager.fillReport(report, params);

            JRPrintPage duplicado = (JRPrintPage) print2.getPages().get(0);
            JRPrintPage triplicado = (JRPrintPage) print3.getPages().get(0);

            print.addPage(duplicado);
            print.addPage(triplicado);

            //JasperViewer.viewReport(print); //El viewer de jasper report
            OutputStream output = new FileOutputStream(new File(pathRArchivoSalida));
            JasperExportManager.exportReportToPdfStream(print, output);
            output.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (JRException jre) {
            throw new RuntimeException(jre);
        }
        return pathRArchivoSalida;
    }

    public static String  impresionComprobanteFiscal(ComprobanteFiscalImpresion comprobanteFiscalImpresion) {
        String nombreJRXML = "comprobanteFiscalTemplate.jrxml";
        String nombreArchivoSalida = (comprobanteFiscalImpresion.getComprobanteTipo()).replace(' ', '_') + "-" + comprobanteFiscalImpresion.getComprobanteLetra() + "-" + comprobanteFiscalImpresion.getComprobanteNumero() + ".pdf";

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL urlSubreportDir = cl.getResource("comprobantesPlantillas/");

        HashMap<String, Object> params = new HashMap<>();
        params.put("SUBREPORT_DIR", urlSubreportDir.getPath());
        params.put("codigoDeBarrasImagen", GeneradorPdf.generarCodigoDeBarrasComprobante(comprobanteFiscalImpresion.getCodigoDeBarras()));
        params.putAll(GeneradorPdf.getDatosEstaticosDelComprobante());
        params.putAll(comprobanteFiscalImpresion.obtenerHashMap());

        return generarReporte(nombreJRXML, nombreArchivoSalida, params);
    }

    /**
     * Retorna lo datos estáticos (información de la empresa) de la cabecera del comprobante.
     *
     * @return
     */
    private static HashMap<String, Object> getDatosEstaticosDelComprobante() {
        HashMap<String, Object> datosEstaticosComprobante = new HashMap<>();
        GestorDeConfiguracion gestorConfiguracion = GestorDeConfiguracion.getInstance();
        datosEstaticosComprobante.put("empresaNombre", gestorConfiguracion.getProperty("empresaNombre"));
        datosEstaticosComprobante.put("empresaDatos1", gestorConfiguracion.getProperty("empresaDatos1"));
        datosEstaticosComprobante.put("empresaDatos2", gestorConfiguracion.getProperty("empresaDatos2"));
        datosEstaticosComprobante.put("empresaDatos3", gestorConfiguracion.getProperty("empresaDatos3"));
        datosEstaticosComprobante.put("empresaDatos4", gestorConfiguracion.getProperty("empresaDatos4"));
        datosEstaticosComprobante.put("empresaTipoContribuyente", gestorConfiguracion.getProperty("empresaTipoContribuyente"));
        datosEstaticosComprobante.put("empresaCuit", gestorConfiguracion.getProperty("empresaCuit"));
        datosEstaticosComprobante.put("empresaIIBB", gestorConfiguracion.getProperty("empresaIIBB"));
        datosEstaticosComprobante.put("empresaEstablecimiento", gestorConfiguracion.getProperty("empresaEstablecimiento"));
        datosEstaticosComprobante.put("empresaSede", gestorConfiguracion.getProperty("empresaSede"));
        datosEstaticosComprobante.put("empresaInicioActividad", gestorConfiguracion.getProperty("empresaInicioActividad"));
        datosEstaticosComprobante.put("comprobantePuntoDeVenta", Formateador.leftPadWithCeros(gestorConfiguracion.getProperty("puntoDeVenta"), 4));
        datosEstaticosComprobante.put("comprobanteLogoPath", gestorConfiguracion.getAbsolutePathConfigurationDir() + "comprobanteLogo.jpg");
        return datosEstaticosComprobante;
    }

    /**
     * Genera el código de barras del comprobante fiscal.
     *
     * @param codigo
     * @return
     */
    private static Image generarCodigoDeBarrasComprobante(String codigo) {
        /*BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCodeType(codeEAN.EAN13);
        codeEAN.setCode(codigo);
        codeEAN.setSize(16f);
        Image imagen = codeEAN.createAwtImage(Color.black, Color.white);
        */

        /*
        Barcode39 code128 = new Barcode39();
        code128.setSize(12f);
	code128.setBaseline(1f);
        code128.setCode(codigo);
        code128.setCodeType(Barcode128.CODE128);

        Image imagen = code128.createAwtImage(Color.black, Color.white);
        */

        Barcode128 code128 = new Barcode128();
        code128.setSize(12f);
        code128.setCode(codigo);
        code128.setGuardBars(true);
        code128.setGenerateChecksum(true);
        code128.setCodeType(Barcode128.CODE128);

        Image barcodeImage = code128.createAwtImage(Color.black, Color.white);

        return barcodeImage;
    }
}
