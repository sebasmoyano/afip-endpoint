
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAERequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAERequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FeCabReq" type="{http://ar.gov.afip.dif.FEV1/}FECAECabRequest" minOccurs="0"/>
 *         &lt;element name="FeDetReq" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfFECAEDetRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAERequest", propOrder = {
    "feCabReq",
    "feDetReq"
})
public class FECAERequest {

    @XmlElement(name = "FeCabReq")
    protected FECAECabRequest feCabReq;
    @XmlElement(name = "FeDetReq")
    protected ArrayOfFECAEDetRequest feDetReq;

    /**
     * Gets the value of the feCabReq property.
     * 
     * @return
     *     possible object is
     *     {@link FECAECabRequest }
     *     
     */
    public FECAECabRequest getFeCabReq() {
        return feCabReq;
    }

    /**
     * Sets the value of the feCabReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAECabRequest }
     *     
     */
    public void setFeCabReq(FECAECabRequest value) {
        this.feCabReq = value;
    }

    /**
     * Gets the value of the feDetReq property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFECAEDetRequest }
     *     
     */
    public ArrayOfFECAEDetRequest getFeDetReq() {
        return feDetReq;
    }

    /**
     * Sets the value of the feDetReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFECAEDetRequest }
     *     
     */
    public void setFeDetReq(ArrayOfFECAEDetRequest value) {
        this.feDetReq = value;
    }

}
