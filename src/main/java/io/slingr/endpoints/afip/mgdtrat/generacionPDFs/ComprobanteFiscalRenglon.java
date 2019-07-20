/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.generacionPDFs;

/**
 *
 * @author itraverso
 */
public class ComprobanteFiscalRenglon {

    private String itemCodigo; //Hasta 9 dígitos o caracteres

    private String itemDescripcion; //Hasta 95 caracteres.

    private Double cantidad; //Hasta 5 dígitos (incluido el punto decimal)

    private Double precioUnidad; //Hasta 9 dígitos (6 dígitos para los enteros, punto y dos dígitos decimales)

    private Double porcentajeBonificacion; //Hasta 5 dígitos (2 dígitos para los enteros, punto y dos dígitos decimales)

    private Double precioTotal; //Hasta 9 dígitos (6 dígitos para los enteros, punto y dos dígitos decimales)

    /**
     * @return the itemCodigo
     */
    public String getItemCodigo() {
        return itemCodigo;
    }

    /**
     * @param itemCodigo the itemCodigo to set
     */
    public void setItemCodigo(String itemCodigo) {
        this.itemCodigo = itemCodigo;
    }

    /**
     * @return the itemDescripcion
     */
    public String getItemDescripcion() {
        return itemDescripcion;
    }

    /**
     * @param itemDescripcion the itemDescripcion to set
     */
    public void setItemDescripcion(String itemDescripcion) {
        this.itemDescripcion = itemDescripcion;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnidad
     */
    public Double getPrecioUnidad() {
        return precioUnidad;
    }

    /**
     * @param precioUnidad the precioUnidad to set
     */
    public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    /**
     * @return the porcentajeBonificacion
     */
    public Double getPorcentajeBonificacion() {
        return porcentajeBonificacion;
    }

    /**
     * @param porcentajeBonificacion the porcentajeBonificacion to set
     */
    public void setPorcentajeBonificacion(Double porcentajeBonificacion) {
        this.porcentajeBonificacion = porcentajeBonificacion;
    }

    /**
     * @return the precioTotal
     */
    public Double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * @param precioTotal the precioTotal to set
     */
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

}
