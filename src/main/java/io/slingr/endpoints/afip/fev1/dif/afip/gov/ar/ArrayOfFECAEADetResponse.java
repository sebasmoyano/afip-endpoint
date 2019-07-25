
package io.slingr.endpoints.afip.fev1.dif.afip.gov.ar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFECAEADetResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFECAEADetResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECAEADetResponse" type="{http://ar.gov.afip.dif.FEV1/}FECAEADetResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFECAEADetResponse", propOrder = {
    "fecaeaDetResponse"
})
public class ArrayOfFECAEADetResponse {

    @XmlElement(name = "FECAEADetResponse", nillable = true)
    protected List<FECAEADetResponse> fecaeaDetResponse;

    /**
     * Gets the value of the fecaeaDetResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fecaeaDetResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFECAEADetResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FECAEADetResponse }
     * 
     * 
     */
    public List<FECAEADetResponse> getFECAEADetResponse() {
        if (fecaeaDetResponse == null) {
            fecaeaDetResponse = new ArrayList<FECAEADetResponse>();
        }
        return this.fecaeaDetResponse;
    }

}