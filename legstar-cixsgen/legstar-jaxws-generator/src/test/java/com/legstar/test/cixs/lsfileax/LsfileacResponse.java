package com.legstar.test.cixs.lsfileax;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * LegStar/Jaxws Operation Response Wrapper.
 * A wrapper class for the Response.
 * 
 * This class was generated by LegStar Mainframe Web Service adapter generator.
 */

@XmlRootElement(name = "LsfileacResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LsfileacResponse",
         namespace = "http://cixs.test.legstar.com/lsfileax",
         propOrder = {
    "response"
})
public class LsfileacResponse {
    
    /** Inner JAXB-bound object. */
    @XmlElement(name = "LsfileacResponseHolder",
                namespace = "http://cixs.test.legstar.com/lsfileax",
                required = true)
    private LsfileacResponseHolder response;

    /**
    * Get the inner JAXB-bound object.
    * 
    * @return JAXB-bound object
    */
    public LsfileacResponseHolder getResponse() {
        return response;
    }

  /**
   * Set the inner JAXB-bound object.
   * 
   * @param value JAXB-bound object
   */
    public void setResponse(
            final LsfileacResponseHolder value) {
        response = value;
    }

}
