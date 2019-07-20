
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
 *         &lt;element name="FEParamGetTiposTributosResult" type="{http://ar.gov.afip.dif.FEV1/}FETributoResponse" minOccurs="0"/>
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
    "feParamGetTiposTributosResult"
})
@XmlRootElement(name = "FEParamGetTiposTributosResponse")
public class FEParamGetTiposTributosResponse {

    @XmlElement(name = "FEParamGetTiposTributosResult")
    protected FETributoResponse feParamGetTiposTributosResult;

    /**
     * Gets the value of the feParamGetTiposTributosResult property.
     * 
     * @return
     *     possible object is
     *     {@link FETributoResponse }
     *     
     */
    public FETributoResponse getFEParamGetTiposTributosResult() {
        return feParamGetTiposTributosResult;
    }

    /**
     * Sets the value of the feParamGetTiposTributosResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FETributoResponse }
     *     
     */
    public void setFEParamGetTiposTributosResult(FETributoResponse value) {
        this.feParamGetTiposTributosResult = value;
    }

}
