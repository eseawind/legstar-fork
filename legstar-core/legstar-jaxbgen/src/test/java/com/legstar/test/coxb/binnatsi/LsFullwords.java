
package com.legstar.test.coxb.binnatsi;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for LsFullwords complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LsFullwords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LsPs9X9Min">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X9Low">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X9High">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X9Max">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
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
@XmlType(name = "LsFullwords", propOrder = {
    "lsPs9X9Min",
    "lsPs9X9Low",
    "lsPs9X9High",
    "lsPs9X9Max"
})
public class LsFullwords
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "LsPs9X9Min")
    @CobolElement(cobolName = "LS-PS9X9-MIN", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "COMP-5", srceLine = 79)
    protected int lsPs9X9Min;
    @XmlElement(name = "LsPs9X9Low")
    @CobolElement(cobolName = "LS-PS9X9-LOW", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "COMP-5", srceLine = 80)
    protected int lsPs9X9Low;
    @XmlElement(name = "LsPs9X9High")
    @CobolElement(cobolName = "LS-PS9X9-HIGH", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "COMP-5", srceLine = 81)
    protected int lsPs9X9High;
    @XmlElement(name = "LsPs9X9Max")
    @CobolElement(cobolName = "LS-PS9X9-MAX", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "COMP-5", srceLine = 82)
    protected int lsPs9X9Max;

    /**
     * Gets the value of the lsPs9X9Min property.
     * 
     */
    public int getLsPs9X9Min() {
        return lsPs9X9Min;
    }

    /**
     * Sets the value of the lsPs9X9Min property.
     * 
     */
    public void setLsPs9X9Min(int value) {
        this.lsPs9X9Min = value;
    }

    public boolean isSetLsPs9X9Min() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X9Low property.
     * 
     */
    public int getLsPs9X9Low() {
        return lsPs9X9Low;
    }

    /**
     * Sets the value of the lsPs9X9Low property.
     * 
     */
    public void setLsPs9X9Low(int value) {
        this.lsPs9X9Low = value;
    }

    public boolean isSetLsPs9X9Low() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X9High property.
     * 
     */
    public int getLsPs9X9High() {
        return lsPs9X9High;
    }

    /**
     * Sets the value of the lsPs9X9High property.
     * 
     */
    public void setLsPs9X9High(int value) {
        this.lsPs9X9High = value;
    }

    public boolean isSetLsPs9X9High() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X9Max property.
     * 
     */
    public int getLsPs9X9Max() {
        return lsPs9X9Max;
    }

    /**
     * Sets the value of the lsPs9X9Max property.
     * 
     */
    public void setLsPs9X9Max(int value) {
        this.lsPs9X9Max = value;
    }

    public boolean isSetLsPs9X9Max() {
        return true;
    }

}
