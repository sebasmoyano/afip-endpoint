
package fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAEAResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEAResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FeCabResp" type="{http://ar.gov.afip.dif.FEV1/}FECAEACabResponse" minOccurs="0"/>
 *         &lt;element name="FeDetResp" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfFECAEADetResponse" minOccurs="0"/>
 *         &lt;element name="Events" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfEvt" minOccurs="0"/>
 *         &lt;element name="Errors" type="{http://ar.gov.afip.dif.FEV1/}ArrayOfErr" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAEAResponse", propOrder = {
    "feCabResp",
    "feDetResp",
    "events",
    "errors"
})
public class FECAEAResponse {

    @XmlElement(name = "FeCabResp")
    protected FECAEACabResponse feCabResp;
    @XmlElement(name = "FeDetResp")
    protected ArrayOfFECAEADetResponse feDetResp;
    @XmlElement(name = "Events")
    protected ArrayOfEvt events;
    @XmlElement(name = "Errors")
    protected ArrayOfErr errors;

    /**
     * Gets the value of the feCabResp property.
     * 
     * @return
     *     possible object is
     *     {@link FECAEACabResponse }
     *     
     */
    public FECAEACabResponse getFeCabResp() {
        return feCabResp;
    }

    /**
     * Sets the value of the feCabResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAEACabResponse }
     *     
     */
    public void setFeCabResp(FECAEACabResponse value) {
        this.feCabResp = value;
    }

    /**
     * Gets the value of the feDetResp property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFECAEADetResponse }
     *     
     */
    public ArrayOfFECAEADetResponse getFeDetResp() {
        return feDetResp;
    }

    /**
     * Sets the value of the feDetResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFECAEADetResponse }
     *     
     */
    public void setFeDetResp(ArrayOfFECAEADetResponse value) {
        this.feDetResp = value;
    }

    /**
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEvt }
     *     
     */
    public ArrayOfEvt getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEvt }
     *     
     */
    public void setEvents(ArrayOfEvt value) {
        this.events = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfErr }
     *     
     */
    public ArrayOfErr getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfErr }
     *     
     */
    public void setErrors(ArrayOfErr value) {
        this.errors = value;
    }

}
