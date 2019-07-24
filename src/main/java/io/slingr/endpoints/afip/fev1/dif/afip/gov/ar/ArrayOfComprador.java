
package io.slingr.endpoints.afip.fev1.dif.afip.gov.ar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfComprador complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComprador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Comprador" type="{http://ar.gov.afip.dif.FEV1/}Comprador" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComprador", propOrder = {
    "comprador"
})
public class ArrayOfComprador {

    @XmlElement(name = "Comprador", nillable = true)
    protected List<Comprador> comprador;

    /**
     * Gets the value of the comprador property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comprador property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComprador().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comprador }
     * 
     * 
     */
    public List<Comprador> getComprador() {
        if (comprador == null) {
            comprador = new ArrayList<Comprador>();
        }
        return this.comprador;
    }

}
