
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECAEARegInformativoResult" type="{http://ar.gov.afip.dif.FEV1/}FECAEAResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fecaeaRegInformativoResult"
})
@XmlRootElement(name = "FECAEARegInformativoResponse")
public class FECAEARegInformativoResponse {

    @XmlElement(name = "FECAEARegInformativoResult")
    protected FECAEAResponse fecaeaRegInformativoResult;

    /**
     * Gets the value of the fecaeaRegInformativoResult property.
     * 
     * @return
     *     possible object is
     *     {@link FECAEAResponse }
     *     
     */
    public FECAEAResponse getFECAEARegInformativoResult() {
        return fecaeaRegInformativoResult;
    }

    /**
     * Sets the value of the fecaeaRegInformativoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAEAResponse }
     *     
     */
    public void setFECAEARegInformativoResult(FECAEAResponse value) {
        this.fecaeaRegInformativoResult = value;
    }

}
