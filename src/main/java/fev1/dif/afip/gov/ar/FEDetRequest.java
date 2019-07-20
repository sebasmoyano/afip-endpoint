
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FEDetRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FEDetRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DocTipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DocNro" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteDesde" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteHasta" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CbteFch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImpTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpTotConc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpNeto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpOpEx" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpTrib" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpIVA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FchServDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FchServHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FchVtoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonCotiz" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="CbtesAsoc" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfCbteAsoc" minOccurs="0"/>
 *         &lt;element name="Tributos" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfTributo" minOccurs="0"/>
 *         &lt;element name="Iva" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfAlicIva" minOccurs="0"/>
 *         &lt;element name="Opcionales" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfOpcional" minOccurs="0"/>
 *         &lt;element name="Compradores" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfComprador" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FEDetRequest", propOrder = {
    "concepto",
    "docTipo",
    "docNro",
    "cbteDesde",
    "cbteHasta",
    "cbteFch",
    "impTotal",
    "impTotConc",
    "impNeto",
    "impOpEx",
    "impTrib",
    "impIVA",
    "fchServDesde",
    "fchServHasta",
    "fchVtoPago",
    "monId",
    "monCotiz",
    "cbtesAsoc",
    "tributos",
    "iva",
    "opcionales",
    "compradores"
})
@XmlSeeAlso({
    FECAEADetRequest.class,
    FECAEDetRequest.class
})
public class FEDetRequest {

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
    @XmlElement(name = "ImpTotal")
    protected double impTotal;
    @XmlElement(name = "ImpTotConc")
    protected double impTotConc;
    @XmlElement(name = "ImpNeto")
    protected double impNeto;
    @XmlElement(name = "ImpOpEx")
    protected double impOpEx;
    @XmlElement(name = "ImpTrib")
    protected double impTrib;
    @XmlElement(name = "ImpIVA")
    protected double impIVA;
    @XmlElement(name = "FchServDesde")
    protected String fchServDesde;
    @XmlElement(name = "FchServHasta")
    protected String fchServHasta;
    @XmlElement(name = "FchVtoPago")
    protected String fchVtoPago;
    @XmlElement(name = "MonId")
    protected String monId;
    @XmlElement(name = "MonCotiz")
    protected double monCotiz;
    @XmlElement(name = "CbtesAsoc")
    protected ArrayOfCbteAsoc cbtesAsoc;
    @XmlElement(name = "Tributos")
    protected ArrayOfTributo tributos;
    @XmlElement(name = "Iva")
    protected ArrayOfAlicIva iva;
    @XmlElement(name = "Opcionales")
    protected ArrayOfOpcional opcionales;
    @XmlElement(name = "Compradores")
    protected ArrayOfComprador compradores;

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
     * Gets the value of the impTotal property.
     * 
     */
    public double getImpTotal() {
        return impTotal;
    }

    /**
     * Sets the value of the impTotal property.
     * 
     */
    public void setImpTotal(double value) {
        this.impTotal = value;
    }

    /**
     * Gets the value of the impTotConc property.
     * 
     */
    public double getImpTotConc() {
        return impTotConc;
    }

    /**
     * Sets the value of the impTotConc property.
     * 
     */
    public void setImpTotConc(double value) {
        this.impTotConc = value;
    }

    /**
     * Gets the value of the impNeto property.
     * 
     */
    public double getImpNeto() {
        return impNeto;
    }

    /**
     * Sets the value of the impNeto property.
     * 
     */
    public void setImpNeto(double value) {
        this.impNeto = value;
    }

    /**
     * Gets the value of the impOpEx property.
     * 
     */
    public double getImpOpEx() {
        return impOpEx;
    }

    /**
     * Sets the value of the impOpEx property.
     * 
     */
    public void setImpOpEx(double value) {
        this.impOpEx = value;
    }

    /**
     * Gets the value of the impTrib property.
     * 
     */
    public double getImpTrib() {
        return impTrib;
    }

    /**
     * Sets the value of the impTrib property.
     * 
     */
    public void setImpTrib(double value) {
        this.impTrib = value;
    }

    /**
     * Gets the value of the impIVA property.
     * 
     */
    public double getImpIVA() {
        return impIVA;
    }

    /**
     * Sets the value of the impIVA property.
     * 
     */
    public void setImpIVA(double value) {
        this.impIVA = value;
    }

    /**
     * Gets the value of the fchServDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchServDesde() {
        return fchServDesde;
    }

    /**
     * Sets the value of the fchServDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchServDesde(String value) {
        this.fchServDesde = value;
    }

    /**
     * Gets the value of the fchServHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchServHasta() {
        return fchServHasta;
    }

    /**
     * Sets the value of the fchServHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchServHasta(String value) {
        this.fchServHasta = value;
    }

    /**
     * Gets the value of the fchVtoPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFchVtoPago() {
        return fchVtoPago;
    }

    /**
     * Sets the value of the fchVtoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFchVtoPago(String value) {
        this.fchVtoPago = value;
    }

    /**
     * Gets the value of the monId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonId() {
        return monId;
    }

    /**
     * Sets the value of the monId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonId(String value) {
        this.monId = value;
    }

    /**
     * Gets the value of the monCotiz property.
     * 
     */
    public double getMonCotiz() {
        return monCotiz;
    }

    /**
     * Sets the value of the monCotiz property.
     * 
     */
    public void setMonCotiz(double value) {
        this.monCotiz = value;
    }

    /**
     * Gets the value of the cbtesAsoc property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCbteAsoc }
     *     
     */
    public ArrayOfCbteAsoc getCbtesAsoc() {
        return cbtesAsoc;
    }

    /**
     * Sets the value of the cbtesAsoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCbteAsoc }
     *     
     */
    public void setCbtesAsoc(ArrayOfCbteAsoc value) {
        this.cbtesAsoc = value;
    }

    /**
     * Gets the value of the tributos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTributo }
     *     
     */
    public ArrayOfTributo getTributos() {
        return tributos;
    }

    /**
     * Sets the value of the tributos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTributo }
     *     
     */
    public void setTributos(ArrayOfTributo value) {
        this.tributos = value;
    }

    /**
     * Gets the value of the iva property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAlicIva }
     *     
     */
    public ArrayOfAlicIva getIva() {
        return iva;
    }

    /**
     * Sets the value of the iva property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAlicIva }
     *     
     */
    public void setIva(ArrayOfAlicIva value) {
        this.iva = value;
    }

    /**
     * Gets the value of the opcionales property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOpcional }
     *     
     */
    public ArrayOfOpcional getOpcionales() {
        return opcionales;
    }

    /**
     * Sets the value of the opcionales property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOpcional }
     *     
     */
    public void setOpcionales(ArrayOfOpcional value) {
        this.opcionales = value;
    }

    /**
     * Gets the value of the compradores property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprador }
     *     
     */
    public ArrayOfComprador getCompradores() {
        return compradores;
    }

    /**
     * Sets the value of the compradores property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprador }
     *     
     */
    public void setCompradores(ArrayOfComprador value) {
        this.compradores = value;
    }

}
