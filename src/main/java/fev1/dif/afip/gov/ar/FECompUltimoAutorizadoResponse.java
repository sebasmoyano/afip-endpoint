
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
 *         &lt;element name="FECompUltimoAutorizadoResult" type="{http://ar.gov.afip.dif.FEV1/}FERecuperaLastCbteResponse" minOccurs="0"/>
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
    "feCompUltimoAutorizadoResult"
})
@XmlRootElement(name = "FECompUltimoAutorizadoResponse")
public class FECompUltimoAutorizadoResponse {

    @XmlElement(name = "FECompUltimoAutorizadoResult")
    protected FERecuperaLastCbteResponse feCompUltimoAutorizadoResult;

    /**
     * Gets the value of the feCompUltimoAutorizadoResult property.
     * 
     * @return
     *     possible object is
     *     {@link FERecuperaLastCbteResponse }
     *     
     */
    public FERecuperaLastCbteResponse getFECompUltimoAutorizadoResult() {
        return feCompUltimoAutorizadoResult;
    }

    /**
     * Sets the value of the feCompUltimoAutorizadoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FERecuperaLastCbteResponse }
     *     
     */
    public void setFECompUltimoAutorizadoResult(FERecuperaLastCbteResponse value) {
        this.feCompUltimoAutorizadoResult = value;
    }

}
