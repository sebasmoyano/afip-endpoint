/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.generacionPDFs;

/**
 * Esta clase la saqué del proyecto del Seba (Impresión
 * utilizando controladora fiscal). Respete los nombres de las
 * propiedades, para que sea mas facil integrar con el Seba.
 *
 * @author itraverso
 */
public class Comprador {

    private String nombre; //Apellido y Nombre o Razón Social

    private String documentoTipo; //CUIT o DNI (de la forma que aparecerá en el comprobante pdf).

    private String documentoNumero;

    private String responsabilidadIva; //Condición frente al IVA

    private String domicilioComercial;



    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the documentoTipo
     */
    public String getDocumentoTipo() {
        return documentoTipo;
    }

    /**
     * @param documentoTipo the documentoTipo to set
     */
    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    /**
     * @return the documentoNumero
     */
    public String getDocumentoNumero() {
        return documentoNumero;
    }

    /**
     * @param documentoNumero the documentoNumero to set
     */
    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    /**
     * @return the responsabilidadIva
     */
    public String getResponsabilidadIva() {
        return responsabilidadIva;
    }

    /**
     * @param responsabilidadIva the responsabilidadIva to set
     */
    public void setResponsabilidadIva(String responsabilidadIva) {
        this.responsabilidadIva = responsabilidadIva;
    }

    /**
     * @return the domicilioComercial
     */
    public String getDomicilioComercial() {
        return domicilioComercial;
    }

    /**
     * @param domicilioComercial the domicilioComercial to set
     */
    public void setDomicilioComercial(String domicilioComercial) {
        this.domicilioComercial = domicilioComercial;
    }
}
