/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.wsafip;

import java.util.Date;

/**
 *
 * @author itraverso
 */
public class CAEResult {

    private Long comprobanteNumero;

    private Long caeNumero;

    private Date caeFechaVencimiento;

    private String caeObservaciones;

    private String caeEventos;

    /**
     * @return the comprobanteNumero
     */
    public Long getComprobanteNumero() {
        return comprobanteNumero;
    }

    /**
     * @param comprobanteNumero the comprobanteNumero to set
     */
    public void setComprobanteNumero(Long comprobanteNumero) {
        this.comprobanteNumero = comprobanteNumero;
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
     * @return the caeEventos
     */
    public String getCaeEventos() {
        return caeEventos;
    }

    /**
     * @param caeEventos the caeEventos to set
     */
    public void setCaeEventos(String caeEventos) {
        this.caeEventos = caeEventos;
    }
}
