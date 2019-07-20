
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
 *         &lt;element name="FEParamGetTiposCbteResult" type="{http://ar.gov.afip.dif.FEV1/}CbteTipoResponse" minOccurs="0"/>
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
    "feParamGetTiposCbteResult"
})
@XmlRootElement(name = "FEParamGetTiposCbteResponse")
public class FEParamGetTiposCbteResponse {

    @XmlElement(name = "FEParamGetTiposCbteResult")
    protected CbteTipoResponse feParamGetTiposCbteResult;

    /**
     * Gets the value of the feParamGetTiposCbteResult property.
     * 
     * @return
     *     possible object is
     *     {@link CbteTipoResponse }
     *     
     */
    public CbteTipoResponse getFEParamGetTiposCbteResult() {
        return feParamGetTiposCbteResult;
    }

    /**
     * Sets the value of the feParamGetTiposCbteResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CbteTipoResponse }
     *     
     */
    public void setFEParamGetTiposCbteResult(CbteTipoResponse value) {
        this.feParamGetTiposCbteResult = value;
    }

}
