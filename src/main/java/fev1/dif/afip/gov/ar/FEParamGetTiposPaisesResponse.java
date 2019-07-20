
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
 *         &lt;element name="FEParamGetTiposPaisesResult" type="{http://ar.gov.afip.dif.FEV1/}FEPaisResponse" minOccurs="0"/>
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
    "feParamGetTiposPaisesResult"
})
@XmlRootElement(name = "FEParamGetTiposPaisesResponse")
public class FEParamGetTiposPaisesResponse {

    @XmlElement(name = "FEParamGetTiposPaisesResult")
    protected FEPaisResponse feParamGetTiposPaisesResult;

    /**
     * Gets the value of the feParamGetTiposPaisesResult property.
     * 
     * @return
     *     possible object is
     *     {@link FEPaisResponse }
     *     
     */
    public FEPaisResponse getFEParamGetTiposPaisesResult() {
        return feParamGetTiposPaisesResult;
    }

    /**
     * Sets the value of the feParamGetTiposPaisesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FEPaisResponse }
     *     
     */
    public void setFEParamGetTiposPaisesResult(FEPaisResponse value) {
        this.feParamGetTiposPaisesResult = value;
    }

}
