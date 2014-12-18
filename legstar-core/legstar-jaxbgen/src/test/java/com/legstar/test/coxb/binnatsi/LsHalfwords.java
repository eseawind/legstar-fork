
package com.legstar.test.coxb.binnatsi;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for LsHalfwords complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LsHalfwords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LsPs9X4Min">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X4Low">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X4High">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X4Max">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
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
@XmlType(name = "LsHalfwords", propOrder = {
    "lsPs9X4Min",
    "lsPs9X4Low",
    "lsPs9X4High",
    "lsPs9X4Max"
})
public class LsHalfwords
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "LsPs9X4Min")
    @CobolElement(cobolName = "LS-PS9X4-MIN", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 4, picture = "S9(4)", usage = "COMP-5", srceLine = 74)
    protected short lsPs9X4Min;
    @XmlElement(name = "LsPs9X4Low")
    @CobolElement(cobolName = "LS-PS9X4-LOW", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 4, picture = "S9(4)", usage = "COMP-5", srceLine = 75)
    protected short lsPs9X4Low;
    @XmlElement(name = "LsPs9X4High")
    @CobolElement(cobolName = "LS-PS9X4-HIGH", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 4, picture = "S9(4)", usage = "COMP-5", srceLine = 76)
    protected short lsPs9X4High;
    @XmlElement(name = "LsPs9X4Max")
    @CobolElement(cobolName = "LS-PS9X4-MAX", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 4, picture = "S9(4)", usage = "COMP-5", srceLine = 77)
    protected short lsPs9X4Max;

    /**
     * Gets the value of the lsPs9X4Min property.
     * 
     */
    public short getLsPs9X4Min() {
        return lsPs9X4Min;
    }

    /**
     * Sets the value of the lsPs9X4Min property.
     * 
     */
    public void setLsPs9X4Min(short value) {
        this.lsPs9X4Min = value;
    }

    public boolean isSetLsPs9X4Min() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X4Low property.
     * 
     */
    public short getLsPs9X4Low() {
        return lsPs9X4Low;
    }

    /**
     * Sets the value of the lsPs9X4Low property.
     * 
     */
    public void setLsPs9X4Low(short value) {
        this.lsPs9X4Low = value;
    }

    public boolean isSetLsPs9X4Low() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X4High property.
     * 
     */
    public short getLsPs9X4High() {
        return lsPs9X4High;
    }

    /**
     * Sets the value of the lsPs9X4High property.
     * 
     */
    public void setLsPs9X4High(short value) {
        this.lsPs9X4High = value;
    }

    public boolean isSetLsPs9X4High() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X4Max property.
     * 
     */
    public short getLsPs9X4Max() {
        return lsPs9X4Max;
    }

    /**
     * Sets the value of the lsPs9X4Max property.
     * 
     */
    public void setLsPs9X4Max(short value) {
        this.lsPs9X4Max = value;
    }

    public boolean isSetLsPs9X4Max() {
        return true;
    }

}
