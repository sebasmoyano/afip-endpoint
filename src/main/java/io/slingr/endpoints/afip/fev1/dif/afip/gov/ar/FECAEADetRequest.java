
package io.slingr.endpoints.afip.fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAEADetRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEADetRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ar.gov.afip.dif.FEV1/}FEDetRequest">
 *       &lt;sequence>
 *         &lt;element name="CAEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CbteFchHsGen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAEADetRequest", propOrder = {
    "caea",
    "cbteFchHsGen"
})
public class FECAEADetRequest
    extends FEDetRequest
{

    @XmlElement(name = "CAEA")
    protected String caea;
    @XmlElement(name = "CbteFchHsGen")
    protected String cbteFchHsGen;

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
     * Gets the value of the cbteFchHsGen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbteFchHsGen() {
        return cbteFchHsGen;
    }

    /**
     * Sets the value of the cbteFchHsGen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbteFchHsGen(String value) {
        this.cbteFchHsGen = value;
    }

}
