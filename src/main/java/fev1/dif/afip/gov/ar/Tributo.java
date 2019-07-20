
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tributo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tributo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BaseImp" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Alic" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tributo", propOrder = {
    "id",
    "desc",
    "baseImp",
    "alic",
    "importe"
})
public class Tributo {

    @XmlElement(name = "Id")
    protected short id;
    @XmlElement(name = "Desc")
    protected String desc;
    @XmlElement(name = "BaseImp")
    protected double baseImp;
    @XmlElement(name = "Alic")
    protected double alic;
    @XmlElement(name = "Importe")
    protected double importe;

    /**
     * Gets the value of the id property.
     * 
     */
    public short getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(short value) {
        this.id = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the baseImp property.
     * 
     */
    public double getBaseImp() {
        return baseImp;
    }

    /**
     * Sets the value of the baseImp property.
     * 
     */
    public void setBaseImp(double value) {
        this.baseImp = value;
    }

    /**
     * Gets the value of the alic property.
     * 
     */
    public double getAlic() {
        return alic;
    }

    /**
     * Sets the value of the alic property.
     * 
     */
    public void setAlic(double value) {
        this.alic = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     */
    public void setImporte(double value) {
        this.importe = value;
    }

}
