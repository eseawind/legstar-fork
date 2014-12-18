
package com.legstar.test.coxb.MSNSearch;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for SearchResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Responses" type="{http://schemas.microsoft.com/MSNSearch/2005/09/fex}ArrayOfSourceResponseResponses"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResponse", propOrder = {
    "responses"
})
public class SearchResponseType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Responses", required = true)
    @CobolElement(cobolName = "Responses", type = CobolType.GROUP_ITEM, levelNumber = 5)
    protected ArrayOfSourceResponseResponsesType responses;

    /**
     * Gets the value of the responses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSourceResponseResponsesType }
     *     
     */
    public ArrayOfSourceResponseResponsesType getResponses() {
        return responses;
    }

    /**
     * Sets the value of the responses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSourceResponseResponsesType }
     *     
     */
    public void setResponses(ArrayOfSourceResponseResponsesType value) {
        this.responses = value;
    }

    public boolean isSetResponses() {
        return (this.responses!= null);
    }

}
