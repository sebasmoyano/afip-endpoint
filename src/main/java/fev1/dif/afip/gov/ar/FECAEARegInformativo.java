
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
 *         &lt;element name="Auth" type="{http://ar.gov.afip.dif.FEV1/}FEAuthRequest" minOccurs="0"/>
 *         &lt;element name="FeCAEARegInfReq" type="{http://ar.gov.afip.dif.FEV1/}FECAEARequest" minOccurs="0"/>
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
    "auth",
    "feCAEARegInfReq"
})
@XmlRootElement(name = "FECAEARegInformativo")
public class FECAEARegInformativo {

    @XmlElement(name = "Auth")
    protected FEAuthRequest auth;
    @XmlElement(name = "FeCAEARegInfReq")
    protected FECAEARequest feCAEARegInfReq;

    /**
     * Gets the value of the auth property.
     * 
     * @return
     *     possible object is
     *     {@link FEAuthRequest }
     *     
     */
    public FEAuthRequest getAuth() {
        return auth;
    }

    /**
     * Sets the value of the auth property.
     * 
     * @param value
     *     allowed object is
     *     {@link FEAuthRequest }
     *     
     */
    public void setAuth(FEAuthRequest value) {
        this.auth = value;
    }

    /**
     * Gets the value of the feCAEARegInfReq property.
     * 
     * @return
     *     possible object is
     *     {@link FECAEARequest }
     *     
     */
    public FECAEARequest getFeCAEARegInfReq() {
        return feCAEARegInfReq;
    }

    /**
     * Sets the value of the feCAEARegInfReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAEARequest }
     *     
     */
    public void setFeCAEARegInfReq(FECAEARequest value) {
        this.feCAEARegInfReq = value;
    }

}
