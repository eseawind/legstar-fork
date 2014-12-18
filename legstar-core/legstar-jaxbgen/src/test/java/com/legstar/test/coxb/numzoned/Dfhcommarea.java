
package com.legstar.test.coxb.numzoned;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for Dfhcommarea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dfhcommarea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LU">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedShort">
 *               &lt;totalDigits value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LS">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LSSignL">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LSSignT">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LSSignSL">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LSSignST">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="2"/>
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
@XmlType(name = "Dfhcommarea", propOrder = {
    "lu",
    "ls",
    "lsSignL",
    "lsSignT",
    "lsSignSL",
    "lsSignST"
})
public class Dfhcommarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "LU")
    @CobolElement(cobolName = "L-U", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 1, picture = "9(1)", usage = "DISPLAY", srceLine = 31)
    protected int lu;
    @XmlElement(name = "LS")
    @CobolElement(cobolName = "L-S", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 2, picture = "S9(2)", usage = "DISPLAY", srceLine = 32)
    protected short ls;
    @XmlElement(name = "LSSignL")
    @CobolElement(cobolName = "L-S-SIGN-L", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, isSignLeading = true, totalDigits = 3, picture = "S9(3)", usage = "DISPLAY", srceLine = 33)
    protected short lsSignL;
    @XmlElement(name = "LSSignT")
    @CobolElement(cobolName = "L-S-SIGN-T", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 2, picture = "S9(2)", usage = "DISPLAY", srceLine = 34)
    protected short lsSignT;
    @XmlElement(name = "LSSignSL")
    @CobolElement(cobolName = "L-S-SIGN-S-L", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, isSignLeading = true, isSignSeparate = true, totalDigits = 1, picture = "S9(1)", usage = "DISPLAY", srceLine = 35)
    protected short lsSignSL;
    @XmlElement(name = "LSSignST")
    @CobolElement(cobolName = "L-S-SIGN-S-T", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, isSignSeparate = true, totalDigits = 2, picture = "S9(2)", usage = "DISPLAY", srceLine = 36)
    protected short lsSignST;

    /**
     * Gets the value of the lu property.
     * 
     */
    public int getLU() {
        return lu;
    }

    /**
     * Sets the value of the lu property.
     * 
     */
    public void setLU(int value) {
        this.lu = value;
    }

    public boolean isSetLU() {
        return true;
    }

    /**
     * Gets the value of the ls property.
     * 
     */
    public short getLS() {
        return ls;
    }

    /**
     * Sets the value of the ls property.
     * 
     */
    public void setLS(short value) {
        this.ls = value;
    }

    public boolean isSetLS() {
        return true;
    }

    /**
     * Gets the value of the lsSignL property.
     * 
     */
    public short getLSSignL() {
        return lsSignL;
    }

    /**
     * Sets the value of the lsSignL property.
     * 
     */
    public void setLSSignL(short value) {
        this.lsSignL = value;
    }

    public boolean isSetLSSignL() {
        return true;
    }

    /**
     * Gets the value of the lsSignT property.
     * 
     */
    public short getLSSignT() {
        return lsSignT;
    }

    /**
     * Sets the value of the lsSignT property.
     * 
     */
    public void setLSSignT(short value) {
        this.lsSignT = value;
    }

    public boolean isSetLSSignT() {
        return true;
    }

    /**
     * Gets the value of the lsSignSL property.
     * 
     */
    public short getLSSignSL() {
        return lsSignSL;
    }

    /**
     * Sets the value of the lsSignSL property.
     * 
     */
    public void setLSSignSL(short value) {
        this.lsSignSL = value;
    }

    public boolean isSetLSSignSL() {
        return true;
    }

    /**
     * Gets the value of the lsSignST property.
     * 
     */
    public short getLSSignST() {
        return lsSignST;
    }

    /**
     * Sets the value of the lsSignST property.
     * 
     */
    public void setLSSignST(short value) {
        this.lsSignST = value;
    }

    public boolean isSetLSSignST() {
        return true;
    }

}
