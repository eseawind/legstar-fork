
package com.legstar.test.coxb.lsfileal;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for ReplyErrorHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReplyErrorHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReplyResp">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ReplyResp2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ReplyMessage">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReplyErrorHeader", propOrder = {
    "replyResp",
    "replyResp2",
    "replyMessage"
})
public class ReplyErrorHeader
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ReplyResp")
    @CobolElement(cobolName = "REPLY-RESP", type = CobolType.BINARY_ITEM, levelNumber = 10, isSigned = true, totalDigits = 8, picture = "S9(8)", usage = "BINARY", srceLine = 62)
    protected int replyResp;
    @XmlElement(name = "ReplyResp2")
    @CobolElement(cobolName = "REPLY-RESP2", type = CobolType.BINARY_ITEM, levelNumber = 10, isSigned = true, totalDigits = 8, picture = "S9(8)", usage = "BINARY", srceLine = 63)
    protected int replyResp2;
    @XmlElement(name = "ReplyMessage", required = true)
    @CobolElement(cobolName = "REPLY-MESSAGE", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 10, picture = "X(128)", srceLine = 64)
    protected String replyMessage;

    /**
     * Gets the value of the replyResp property.
     * 
     */
    public int getReplyResp() {
        return replyResp;
    }

    /**
     * Sets the value of the replyResp property.
     * 
     */
    public void setReplyResp(int value) {
        this.replyResp = value;
    }

    public boolean isSetReplyResp() {
        return true;
    }

    /**
     * Gets the value of the replyResp2 property.
     * 
     */
    public int getReplyResp2() {
        return replyResp2;
    }

    /**
     * Sets the value of the replyResp2 property.
     * 
     */
    public void setReplyResp2(int value) {
        this.replyResp2 = value;
    }

    public boolean isSetReplyResp2() {
        return true;
    }

    /**
     * Gets the value of the replyMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyMessage() {
        return replyMessage;
    }

    /**
     * Sets the value of the replyMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyMessage(String value) {
        this.replyMessage = value;
    }

    public boolean isSetReplyMessage() {
        return (this.replyMessage!= null);
    }

}
