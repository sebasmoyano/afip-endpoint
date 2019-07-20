
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FEDetResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FEDetResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DocTipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DocNro" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteDesde" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteHasta" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteFch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "FEDetResponse", propOrder = {
    "concepto",
    "docTipo",
    "docNro",
    "cbteDesde",
    "cbteHasta",
    "cbteFch",
    "resultado",
    "observaciones"
})
@XmlSeeAlso({
    FECAEADetResponse.class,
    FECAEDetResponse.class
})
public class FEDetResponse {

    @XmlElement(name = "Concepto")
    protected int concepto;
    @XmlElement(name = "DocTipo")
    protected int docTipo;
    @XmlElement(name = "DocNro")
    protected long docNro;
    @XmlElement(name = "CbteDesde")
    protected long cbteDesde;
    @XmlElement(name = "CbteHasta")
    protected long cbteHasta;
    @XmlElement(name = "CbteFch")
    protected String cbteFch;
    @XmlElement(name = "Resultado")
    protected String resultado;
    @XmlElement(name = "Observaciones")
    protected ArrayOfObs observaciones;

    /**
     * Gets the value of the concepto property.
     * 
     */
    public int getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     */
    public void setConcepto(int value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the docTipo property.
     * 
     */
    public int getDocTipo() {
        return docTipo;
    }

    /**
     * Sets the value of the docTipo property.
     * 
     */
    public void setDocTipo(int value) {
        this.docTipo = value;
    }

    /**
     * Gets the value of the docNro property.
     * 
     */
    public long getDocNro() {
        return docNro;
    }

    /**
     * Sets the value of the docNro property.
     * 
     */
    public void setDocNro(long value) {
        this.docNro = value;
    }

    /**
     * Gets the value of the cbteDesde property.
     * 
     */
    public long getCbteDesde() {
        return cbteDesde;
    }

    /**
     * Sets the value of the cbteDesde property.
     * 
     */
    public void setCbteDesde(long value) {
        this.cbteDesde = value;
    }

    /**
     * Gets the value of the cbteHasta property.
     * 
     */
    public long getCbteHasta() {
        return cbteHasta;
    }

    /**
     * Sets the value of the cbteHasta property.
     * 
     */
    public void setCbteHasta(long value) {
        this.cbteHasta = value;
    }

    /**
     * Gets the value of the cbteFch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbteFch() {
        return cbteFch;
    }

    /**
     * Sets the value of the cbteFch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbteFch(String value) {
        this.cbteFch = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultado(String value) {
        this.resultado = value;
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
