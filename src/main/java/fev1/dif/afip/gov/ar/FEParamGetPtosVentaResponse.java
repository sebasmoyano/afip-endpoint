
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
 *         &lt;element name="FEParamGetPtosVentaResult" type="{http://ar.gov.afip.dif.FEV1/}FEPtoVentaResponse" minOccurs="0"/>
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
    "feParamGetPtosVentaResult"
})
@XmlRootElement(name = "FEParamGetPtosVentaResponse")
public class FEParamGetPtosVentaResponse {

    @XmlElement(name = "FEParamGetPtosVentaResult")
    protected FEPtoVentaResponse feParamGetPtosVentaResult;

    /**
     * Gets the value of the feParamGetPtosVentaResult property.
     * 
     * @return
     *     possible object is
     *     {@link FEPtoVentaResponse }
     *     
     */
    public FEPtoVentaResponse getFEParamGetPtosVentaResult() {
        return feParamGetPtosVentaResult;
    }

    /**
     * Sets the value of the feParamGetPtosVentaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FEPtoVentaResponse }
     *     
     */
    public void setFEParamGetPtosVentaResult(FEPtoVentaResponse value) {
        this.feParamGetPtosVentaResult = value;
    }

}
