/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.generacionPDFs;

import io.slingr.endpoints.afip.mgdtrat.util.Formateador;
import io.slingr.endpoints.afip.mgdtrat.util.GestorDeConfiguracion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase la saqué del proyecto del Seba (Impresión
 * utilizando controladora fiscal).
 * Tuve que cambiar el nombre de algunas propiedades, porque
 * esta clase se utiliza para todos los comprobantes fiscales
 * (factura, nota de débito y crédito), no solo para la factura.
 * Además agregué propiedades que no tenía, como la condición
 * de la venta.
 *
 *
 * @author itraverso
 */
public class ComprobanteFiscalImpresion {

    private String comprobanteLetra;
    private String comprobanteCodigo; //El código de AFIP: según tipo de comprobante y letra.
    private String comprobanteNumero;
    private String comprobanteTipo; //Tipo: Factura, Nota de Débito ó Nota de Crédito. Como aparecerá en el pdf.
    private Date comprobanteFecha;
    private Comprador comprador;
    private String condicionDeVenta;
    private String observaciones;
    private Long caeNumero; //Hasta 16 dígitos
    private Date caeFechaVencimiento;
    private String caeObservaciones;
    private Double descuento; //El total de descuento en pesos
    private Double subtotal;
    private Double iva;
    private Double total;
    private String notaAlPie;

    //En las factuas onlines AFIP lo genera así: CUIT de quien factura + comprobanteCodigo + punto de venta + caeNumero + caeFechaVencimiento (yyyyMMdd)
    private String codigoDeBarras;

    //La cantidad máxima de renglones es 15
    private List<ComprobanteFiscalRenglon> renglones = new ArrayList<ComprobanteFiscalRenglon>();

    /**
     * @return the comprobanteLetra
     */
    public String getComprobanteLetra() {
        return comprobanteLetra;
    }

    /**
     * @param comprobanteLetra the comprobanteLetra to set
     */
    public void setComprobanteLetra(String comprobanteLetra) {
        this.comprobanteLetra = comprobanteLetra;
    }

    /**
     * El código de AFIP del comprobante: según tipo de comprobante y letra.
     *
     * @return the comprobanteCodigo
     */
    public String getComprobanteCodigo() {
        return Formateador.leftPadWithCeros(this.comprobanteCodigo, 3);
    }

    /**
     * @param comprobanteCodigo the comprobanteCodigo to set
     */
    public void setComprobanteCodigo(String comprobanteCodigo) {
        this.comprobanteCodigo = comprobanteCodigo;
    }

    /**
     * @return the comprobanteNumero
     */
    public String getComprobanteNumero() {
        return Formateador.leftPadWithCeros(this.comprobanteNumero, 8);
    }

    /**
     * @param comprobanteNumero the comprobanteNumero to set
     */
    public void setComprobanteNumero(String comprobanteNumero) {
        this.comprobanteNumero = comprobanteNumero;
    }

    /**
     * Tipo: Factura, Nota de Débito ó Nota de Crédito. Como aparecerá en el pdf
     *
     * @return the comprobanteTipo
     */
    public String getComprobanteTipo() {
        return comprobanteTipo;
    }

    /**
     * @param comprobanteTipo the comprobanteTipo to set
     */
    public void setComprobanteTipo(String comprobanteTipo) {
        this.comprobanteTipo = comprobanteTipo;
    }

    /**
     * @return the comprobanteFecha
     */
    public Date getComprobanteFecha() {
        return comprobanteFecha;
    }

    /**
     * @param comprobanteFecha the comprobanteFecha to set
     */
    public void setComprobanteFecha(Date comprobanteFecha) {
        this.comprobanteFecha = comprobanteFecha;
    }

    /**
     * @return the comprador
     */
    public Comprador getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the condicionDeVenta
     */
    public String getCondicionDeVenta() {
        return condicionDeVenta;
    }

    /**
     * @param condicionDeVenta the condicionDeVenta to set
     */
    public void setCondicionDeVenta(String condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the caeNumero
     */
    public Long getCaeNumero() {
        return caeNumero;
    }

    /**
     * @param caeNumero the caeNumero to set
     */
    public void setCaeNumero(Long caeNumero) {
        this.caeNumero = caeNumero;
    }

    /**
     * @return the caeFechaVencimiento
     */
    public Date getCaeFechaVencimiento() {
        return caeFechaVencimiento;
    }

    /**
     * @param caeFechaVencimiento the caeFechaVencimiento to set
     */
    public void setCaeFechaVencimiento(Date caeFechaVencimiento) {
        this.caeFechaVencimiento = caeFechaVencimiento;
    }

    /**
     * @return the caeObservaciones
     */
    public String getCaeObservaciones() {
        return caeObservaciones;
    }

    /**
     * @param caeObservaciones the caeObservaciones to set
     */
    public void setCaeObservaciones(String caeObservaciones) {
        this.caeObservaciones = caeObservaciones;
    }

    /**
     * El total de descuento en pesos
     *
     * @return the descuento
     */
    public Double getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the subtotal
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public Double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(Double iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the notaAlPie
     */
    public String getNotaAlPie() {
        return notaAlPie;
    }

    /**
     * @param notaAlPie the notaAlPie to set
     */
    public void setNotaAlPie(String notaAlPie) {
        this.notaAlPie = notaAlPie;
    }

    /**
     * Si está vacio el código de barras, entonces lo retorno como
     * lo genera la AFIP en las facturas onlines: CUIT de quien factura +
     * comprobanteCodigo + punto de venta + caeNumero + caeFechaVencimiento (yyyyMMdd)
     *
     * @return the codigoDeBarras
     */
    public String getCodigoDeBarras() {
        String codigoDeBarrasRetorno;

        if(codigoDeBarras != null && ! codigoDeBarras.isEmpty()) {
            codigoDeBarrasRetorno = codigoDeBarras;
        } else {
            String cuitEmpresa = GestorDeConfiguracion.getInstance().getProperty("CUIT");
            String puntoDeVenta = Formateador.leftPadWithCeros(GestorDeConfiguracion.getInstance().getProperty("puntoDeVenta"), 4);
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String fechaVencimientoCae = dateFormat.format(this.getCaeFechaVencimiento());

            codigoDeBarrasRetorno = cuitEmpresa + this.getComprobanteCodigo() + puntoDeVenta + this.getCaeNumero() + fechaVencimientoCae;
        }

        return codigoDeBarrasRetorno;
    }

    /**
     * @param codigoDeBarras the codigoDeBarras to set
     */
    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    /**
     * @return the renglones
     */
    public List<ComprobanteFiscalRenglon> getRenglones() {
        return renglones;
    }

    /**
     * @param renglones the renglones to set
     */
    public void setRenglones(List<ComprobanteFiscalRenglon> renglones) {
        this.renglones = renglones;
    }

    /**
     * Retorna un HashMap con la información del objeto
     * ComprobanteFiscalImpresion, el cual se utiliza
     * para llenar el template de impresión de japer
     * y generar el pdf.
     * @return
     */
    public HashMap<String, Object> obtenerHashMap() {
        HashMap<String, Object> datosComprobante = new HashMap<>();

        datosComprobante.put("comprobanteLetra", this.getComprobanteLetra());
        datosComprobante.put("comprobanteCodigo", this.getComprobanteCodigo());
        datosComprobante.put("comprobanteNumero", this.getComprobanteNumero());
        datosComprobante.put("comprobanteTipo", this.getComprobanteTipo());
        datosComprobante.put("comprobanteFecha", this.getComprobanteFecha());

        //Comprador
        datosComprobante.put("clienteNombre", this.getComprador().getNombre());
        datosComprobante.put("clienteDomicilio", this.getComprador().getDomicilioComercial());
        datosComprobante.put("clienteTipoContribuyente", this.getComprador().getResponsabilidadIva());
        datosComprobante.put("clienteDocumentoTipo", this.getComprador().getDocumentoTipo());
        datosComprobante.put("clienteDocumentoNumero", this.getComprador().getDocumentoNumero());

        datosComprobante.put("condicionDeVenta", this.getCondicionDeVenta());
        datosComprobante.put("observaciones", this.getObservaciones());
        datosComprobante.put("caeNumero", this.getCaeNumero());
        datosComprobante.put("caeFechaVencimiento", this.getCaeFechaVencimiento());
        datosComprobante.put("caeObservaciones", this.getCaeObservaciones());
        datosComprobante.put("descuento", this.getDescuento());
        datosComprobante.put("subtotal", this.getSubtotal());
        datosComprobante.put("iva", this.getIva());
        datosComprobante.put("total", this.getTotal());
        datosComprobante.put("notaAlPie", this.getNotaAlPie());
        datosComprobante.put("codigoDeBarras", this.getCodigoDeBarras());

        //Renglones Comprobante Fiscal
        List<HashMap<String, Object>> comprobanteRenglones = new ArrayList<>();

        for (ComprobanteFiscalRenglon renglon : this.getRenglones()) {
            HashMap<String, Object> comprobanteRenglon = new HashMap<>();
            comprobanteRenglon.put("itemCodigo", renglon.getItemCodigo());
            comprobanteRenglon.put("itemDescripcion", renglon.getItemDescripcion());
            comprobanteRenglon.put("cantidad", renglon.getCantidad());
            comprobanteRenglon.put("precioUnidad", renglon.getPrecioUnidad());
            comprobanteRenglon.put("porcentajeBonificacion", renglon.getPorcentajeBonificacion());
            comprobanteRenglon.put("precioTotal", renglon.getPrecioTotal());

            comprobanteRenglones.add(comprobanteRenglon);
        }

        datosComprobante.put("comprobanteRenglones", comprobanteRenglones);

        return datosComprobante;
    }
}
