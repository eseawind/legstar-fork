
package com.legstar.test.coxb.fixarnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="CArrayPd" maxOccurs="3" minOccurs="3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="7"/>
 *               &lt;fractionDigits value="2"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CArrayZd" maxOccurs="3" minOccurs="3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="6"/>
 *               &lt;fractionDigits value="3"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CArrayZi" maxOccurs="3" minOccurs="3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedShort">
 *               &lt;totalDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CArrayBi" maxOccurs="3" minOccurs="3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CArrayNi" maxOccurs="3" minOccurs="3">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedLong">
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
    "cArrayPd",
    "cArrayZd",
    "cArrayZi",
    "cArrayBi",
    "cArrayNi"
})
public class Dfhcommarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "CArrayPd", required = true)
    @CobolElement(cobolName = "C-ARRAY-PD", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 7, fractionDigits = 2, minOccurs = 3, maxOccurs = 3, picture = "9(5)V99", usage = "PACKED-DECIMAL", srceLine = 23)
    protected List<BigDecimal> cArrayPd;
    @XmlElement(name = "CArrayZd", required = true)
    @CobolElement(cobolName = "C-ARRAY-ZD", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 6, fractionDigits = 3, minOccurs = 3, maxOccurs = 3, picture = "9(3)V999", srceLine = 24)
    protected List<BigDecimal> cArrayZd;
    @XmlElement(name = "CArrayZi", type = Integer.class)
    @CobolElement(cobolName = "C-ARRAY-ZI", type = CobolType.ZONED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 4, minOccurs = 3, maxOccurs = 3, picture = "9(4)", srceLine = 25)
    protected List<Integer> cArrayZi;
    @XmlElement(name = "CArrayBi", type = Long.class)
    @CobolElement(cobolName = "C-ARRAY-BI", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 9, minOccurs = 3, maxOccurs = 3, picture = "9(9)", usage = "BINARY", srceLine = 26)
    protected List<Long> cArrayBi;
    @XmlElement(name = "CArrayNi", required = true)
    @CobolElement(cobolName = "C-ARRAY-NI", type = CobolType.NATIVE_BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 18, minOccurs = 3, maxOccurs = 3, picture = "9(18)", usage = "COMP-5", srceLine = 27)
    protected List<BigInteger> cArrayNi;

    /**
     * Gets the value of the cArrayPd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cArrayPd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCArrayPd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getCArrayPd() {
        if (cArrayPd == null) {
            cArrayPd = new ArrayList<BigDecimal>();
        }
        return this.cArrayPd;
    }

    public boolean isSetCArrayPd() {
        return ((this.cArrayPd!= null)&&(!this.cArrayPd.isEmpty()));
    }

    public void unsetCArrayPd() {
        this.cArrayPd = null;
    }

    /**
     * Gets the value of the cArrayZd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cArrayZd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCArrayZd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getCArrayZd() {
        if (cArrayZd == null) {
            cArrayZd = new ArrayList<BigDecimal>();
        }
        return this.cArrayZd;
    }

    public boolean isSetCArrayZd() {
        return ((this.cArrayZd!= null)&&(!this.cArrayZd.isEmpty()));
    }

    public void unsetCArrayZd() {
        this.cArrayZd = null;
    }

    /**
     * Gets the value of the cArrayZi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cArrayZi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCArrayZi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getCArrayZi() {
        if (cArrayZi == null) {
            cArrayZi = new ArrayList<Integer>();
        }
        return this.cArrayZi;
    }

    public boolean isSetCArrayZi() {
        return ((this.cArrayZi!= null)&&(!this.cArrayZi.isEmpty()));
    }

    public void unsetCArrayZi() {
        this.cArrayZi = null;
    }

    /**
     * Gets the value of the cArrayBi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cArrayBi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCArrayBi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCArrayBi() {
        if (cArrayBi == null) {
            cArrayBi = new ArrayList<Long>();
        }
        return this.cArrayBi;
    }

    public boolean isSetCArrayBi() {
        return ((this.cArrayBi!= null)&&(!this.cArrayBi.isEmpty()));
    }

    public void unsetCArrayBi() {
        this.cArrayBi = null;
    }

    /**
     * Gets the value of the cArrayNi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cArrayNi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCArrayNi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getCArrayNi() {
        if (cArrayNi == null) {
            cArrayNi = new ArrayList<BigInteger>();
        }
        return this.cArrayNi;
    }

    public boolean isSetCArrayNi() {
        return ((this.cArrayNi!= null)&&(!this.cArrayNi.isEmpty()));
    }

    public void unsetCArrayNi() {
        this.cArrayNi = null;
    }

}
