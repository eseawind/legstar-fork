
package com.legstar.test.cixs.lsfilead;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.test.coxb.lsfilead.Dfhcommarea;


/**
 * <p>Java class for LsfileadRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LsfileadRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://legstar.com/test/coxb/lsfilead}Dfhcommarea"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LsfileadRequest", propOrder = {
    "dfhcommarea"
})
public class LsfileadRequest {

    @XmlElement(name = "Dfhcommarea", namespace = "http://legstar.com/test/coxb/lsfilead", required = true)
    protected Dfhcommarea dfhcommarea;

    /**
     * Gets the value of the dfhcommarea property.
     * 
     * @return
     *     possible object is
     *     {@link Dfhcommarea }
     *     
     */
    public Dfhcommarea getDfhcommarea() {
        return dfhcommarea;
    }

    /**
     * Sets the value of the dfhcommarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dfhcommarea }
     *     
     */
    public void setDfhcommarea(Dfhcommarea value) {
        this.dfhcommarea = value;
    }

}
