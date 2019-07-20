
package fev1.dif.afip.gov.ar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFECAEDetRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFECAEDetRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECAEDetRequest" type="{http://ar.gov.afip.dif.FEV1/}FECAEDetRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFECAEDetRequest", propOrder = {
    "fecaeDetRequest"
})
public class ArrayOfFECAEDetRequest {

    @XmlElement(name = "FECAEDetRequest", nillable = true)
    protected List<FECAEDetRequest> fecaeDetRequest;

    /**
     * Gets the value of the fecaeDetRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fecaeDetRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFECAEDetRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FECAEDetRequest }
     * 
     * 
     */
    public List<FECAEDetRequest> getFECAEDetRequest() {
        if (fecaeDetRequest == null) {
            fecaeDetRequest = new ArrayList<FECAEDetRequest>();
        }
        return this.fecaeDetRequest;
    }

}
