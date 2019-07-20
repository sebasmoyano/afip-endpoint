
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAEAGet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEAGet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CAEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Periodo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Orden" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="FchVigDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FchVigHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FchTopeInf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FchProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfObs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAEAGet", propOrder = {
    "caea",
    "periodo",
    "orden",
    "fchVigDesde",
    "fchVigHasta",
    "fchTopeInf",
    "fchProceso",
    "observaciones"
})
public class FECAEAGet {

    @XmlElement(name = "CAEA")
    protected String caea;
    @XmlElement(name = "Periodo")
    protected int periodo;
    @XmlElement(name = "Orden")
    protected short orden;
    @XmlElement(name = "FchVigDesde")
    protected String fchVigDesde;
    @XmlElement(name = "FchVigHasta")
    protected String fchVigHasta;
    @XmlElement(name = "FchTopeInf")
    protected String fchTopeInf;
    @XmlElement(name = "FchProceso")
    protected String fchProceso;
    @XmlElement(name = "Observaciones")
    protected ArrayOfObs observaciones;

    /**
     * Gets the value of the caea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAEA() {
        return caea;
    }

    /**
     * Sets the value of the caea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAEA(String value) {
        this.caea = value;
    }

    /**
     * Gets the value of the periodo property.
     * 
     */
    public int getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     */
    public void setPeriodo(int value) {
        this.periodo = value;
    }

    /**
     * Gets the value of the orden property.
     * 
     */
    public short getOrden() {
        return orden;
    }

    /**
     * Sets the value of the orden property.
     * 
     */
    public void setOrden(short value) {
        this.orden = value;
    }

    /**
     * Gets the value of the fchVigDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchVigDesde() {
        return fchVigDesde;
    }

    /**
     * Sets the value of the fchVigDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchVigDesde(String value) {
        this.fchVigDesde = value;
    }

    /**
     * Gets the value of the fchVigHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchVigHasta() {
        return fchVigHasta;
    }

    /**
     * Sets the value of the fchVigHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchVigHasta(String value) {
        this.fchVigHasta = value;
    }

    /**
     * Gets the value of the fchTopeInf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchTopeInf() {
        return fchTopeInf;
    }

    /**
     * Sets the value of the fchTopeInf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchTopeInf(String value) {
        this.fchTopeInf = value;
    }

    /**
     * Gets the value of the fchProceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchProceso() {
        return fchProceso;
    }

    /**
     * Sets the value of the fchProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchProceso(String value) {
        this.fchProceso = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfObs }
     *     
     */
    public ArrayOfObs getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfObs }
     *     
     */
    public void setObservaciones(ArrayOfObs value) {
        this.observaciones = value;
    }

}
