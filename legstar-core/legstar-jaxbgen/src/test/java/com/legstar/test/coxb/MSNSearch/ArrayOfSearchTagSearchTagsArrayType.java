
package com.legstar.test.coxb.MSNSearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for ArrayOfSearchTagSearchTagsArray complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSearchTagSearchTagsArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchTag" type="{http://schemas.microsoft.com/MSNSearch/2005/09/fex}SearchTag" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSearchTagSearchTagsArray", propOrder = {
    "searchTag"
})
public class ArrayOfSearchTagSearchTagsArrayType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "SearchTag")
    @CobolElement(cobolName = "SearchTag", type = CobolType.GROUP_ITEM, levelNumber = 15, minOccurs = 0, maxOccurs = 10)
    protected List<SearchTagType> searchTag;

    /**
     * Gets the value of the searchTag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchTag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchTag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchTagType }
     * 
     * 
     */
    public List<SearchTagType> getSearchTag() {
        if (searchTag == null) {
            searchTag = new ArrayList<SearchTagType>();
        }
        return this.searchTag;
    }

    public boolean isSetSearchTag() {
        return ((this.searchTag!= null)&&(!this.searchTag.isEmpty()));
    }

    public void unsetSearchTag() {
        this.searchTag = null;
    }

}
