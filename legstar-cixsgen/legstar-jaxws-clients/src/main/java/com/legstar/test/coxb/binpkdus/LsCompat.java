
package com.legstar.test.coxb.binpkdus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LsCompat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LsCompat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LsP9X1Null" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LsP9X1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LsP9X2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LsP9X7" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LsP9X18" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LsCompat", propOrder = {
    "lsP9X1Null",
    "lsP9X1",
    "lsP9X2",
    "lsP9X7",
    "lsP9X18"
})
public class LsCompat {

    @XmlElement(name = "LsP9X1Null")
    protected int lsP9X1Null;
    @XmlElement(name = "LsP9X1")
    protected int lsP9X1;
    @XmlElement(name = "LsP9X2")
    protected int lsP9X2;
    @XmlElement(name = "LsP9X7")
    protected long lsP9X7;
    @XmlElement(name = "LsP9X18")
    protected long lsP9X18;

    /**
     * Gets the value of the lsP9X1Null property.
     * 
     */
    public int getLsP9X1Null() {
        return lsP9X1Null;
    }

    /**
     * Sets the value of the lsP9X1Null property.
     * 
     */
    public void setLsP9X1Null(int value) {
        this.lsP9X1Null = value;
    }

    /**
     * Gets the value of the lsP9X1 property.
     * 
     */
    public int getLsP9X1() {
        return lsP9X1;
    }

    /**
     * Sets the value of the lsP9X1 property.
     * 
     */
    public void setLsP9X1(int value) {
        this.lsP9X1 = value;
    }

    /**
     * Gets the value of the lsP9X2 property.
     * 
     */
    public int getLsP9X2() {
        return lsP9X2;
    }

    /**
     * Sets the value of the lsP9X2 property.
     * 
     */
    public void setLsP9X2(int value) {
        this.lsP9X2 = value;
    }

    /**
     * Gets the value of the lsP9X7 property.
     * 
     */
    public long getLsP9X7() {
        return lsP9X7;
    }

    /**
     * Sets the value of the lsP9X7 property.
     * 
     */
    public void setLsP9X7(long value) {
        this.lsP9X7 = value;
    }

    /**
     * Gets the value of the lsP9X18 property.
     * 
     */
    public long getLsP9X18() {
        return lsP9X18;
    }

    /**
     * Sets the value of the lsP9X18 property.
     * 
     */
    public void setLsP9X18(long value) {
        this.lsP9X18 = value;
    }

}
