
package com.legstar.test.coxb.binnatsi;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for LsDoublewords complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LsDoublewords">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LsPs9X18Min">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X18Low">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X18High">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LsPs9X18Max">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
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
@XmlType(name = "LsDoublewords", propOrder = {
    "lsPs9X18Min",
    "lsPs9X18Low",
    "lsPs9X18High",
    "lsPs9X18Max"
})
public class LsDoublewords
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "LsPs9X18Min")
    @CobolElement(cobolName = "LS-PS9X18-MIN", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 18, picture = "S9(18)", usage = "COMP-5", srceLine = 84)
    protected long lsPs9X18Min;
    @XmlElement(name = "LsPs9X18Low")
    @CobolElement(cobolName = "LS-PS9X18-LOW", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 18, picture = "S9(18)", usage = "COMP-5", srceLine = 85)
    protected long lsPs9X18Low;
    @XmlElement(name = "LsPs9X18High")
    @CobolElement(cobolName = "LS-PS9X18-HIGH", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 18, picture = "S9(18)", usage = "COMP-5", srceLine = 86)
    protected long lsPs9X18High;
    @XmlElement(name = "LsPs9X18Max")
    @CobolElement(cobolName = "LS-PS9X18-MAX", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 15, isSigned = true, totalDigits = 18, picture = "S9(18)", usage = "COMP-5", srceLine = 87)
    protected long lsPs9X18Max;

    /**
     * Gets the value of the lsPs9X18Min property.
     * 
     */
    public long getLsPs9X18Min() {
        return lsPs9X18Min;
    }

    /**
     * Sets the value of the lsPs9X18Min property.
     * 
     */
    public void setLsPs9X18Min(long value) {
        this.lsPs9X18Min = value;
    }

    public boolean isSetLsPs9X18Min() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X18Low property.
     * 
     */
    public long getLsPs9X18Low() {
        return lsPs9X18Low;
    }

    /**
     * Sets the value of the lsPs9X18Low property.
     * 
     */
    public void setLsPs9X18Low(long value) {
        this.lsPs9X18Low = value;
    }

    public boolean isSetLsPs9X18Low() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X18High property.
     * 
     */
    public long getLsPs9X18High() {
        return lsPs9X18High;
    }

    /**
     * Sets the value of the lsPs9X18High property.
     * 
     */
    public void setLsPs9X18High(long value) {
        this.lsPs9X18High = value;
    }

    public boolean isSetLsPs9X18High() {
        return true;
    }

    /**
     * Gets the value of the lsPs9X18Max property.
     * 
     */
    public long getLsPs9X18Max() {
        return lsPs9X18Max;
    }

    /**
     * Sets the value of the lsPs9X18Max property.
     * 
     */
    public void setLsPs9X18Max(long value) {
        this.lsPs9X18Max = value;
    }

    public boolean isSetLsPs9X18Max() {
        return true;
    }

}
