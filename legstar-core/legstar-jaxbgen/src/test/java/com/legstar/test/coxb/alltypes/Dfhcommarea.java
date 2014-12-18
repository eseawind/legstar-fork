
package com.legstar.test.coxb.alltypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *         &lt;element name="SString">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SBinary">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}hexBinary">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SShort">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SUshort">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedShort">
 *               &lt;totalDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SInt">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SUint">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SLong">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;totalDigits value="18"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SUlong">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedLong">
 *               &lt;maxInclusive value="999999999999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SXlong">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SUxlong">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="31"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SDec">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="9"/>
 *               &lt;fractionDigits value="2"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SFloat">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SDouble">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AString" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ABinary" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AShort" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *               &lt;totalDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AUshort" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedShort">
 *               &lt;totalDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AInt" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AUint" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ALong" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;totalDigits value="18"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AUlong" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedLong">
 *               &lt;maxInclusive value="999999999999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AXlong" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="31"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AUxlong" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="31"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ADec" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="9"/>
 *               &lt;fractionDigits value="2"/>
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AFloat" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ADouble" maxOccurs="2" minOccurs="2">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
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
    "sString",
    "sBinary",
    "sShort",
    "sUshort",
    "sInt",
    "sUint",
    "sLong",
    "sUlong",
    "sXlong",
    "sUxlong",
    "sDec",
    "sFloat",
    "sDouble",
    "aString",
    "aBinary",
    "aShort",
    "aUshort",
    "aInt",
    "aUint",
    "aLong",
    "aUlong",
    "aXlong",
    "aUxlong",
    "aDec",
    "aFloat",
    "aDouble"
})
public class Dfhcommarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "SString", required = true)
    @CobolElement(cobolName = "S-STRING", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 5, picture = "X(4)", srceLine = 24)
    protected String sString;
    @XmlElement(name = "SBinary", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @CobolElement(cobolName = "S-BINARY", type = CobolType.OCTET_STREAM_ITEM, levelNumber = 5, picture = "X(4)", srceLine = 25)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] sBinary;
    @XmlElement(name = "SShort")
    @CobolElement(cobolName = "S-SHORT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = true, totalDigits = 4, picture = "S9(4)", usage = "BINARY", srceLine = 26)
    protected short sShort;
    @XmlElement(name = "SUshort")
    @CobolElement(cobolName = "S-USHORT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 4, picture = "9(4)", usage = "BINARY", srceLine = 27)
    protected int sUshort;
    @XmlElement(name = "SInt")
    @CobolElement(cobolName = "S-INT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = true, totalDigits = 9, picture = "S9(9)", usage = "BINARY", srceLine = 28)
    protected int sInt;
    @XmlElement(name = "SUint")
    @CobolElement(cobolName = "S-UINT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 9, picture = "9(9)", usage = "BINARY", srceLine = 29)
    protected long sUint;
    @XmlElement(name = "SLong")
    @CobolElement(cobolName = "S-LONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 18, picture = "S9(18)", usage = "PACKED-DECIMAL", srceLine = 30)
    protected long sLong;
    @XmlElement(name = "SUlong")
    @CobolElement(cobolName = "S-ULONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 18, picture = "9(18)", usage = "PACKED-DECIMAL", srceLine = 31)
    protected long sUlong;
    @XmlElement(name = "SXlong", required = true)
    @CobolElement(cobolName = "S-XLONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 31, picture = "S9(31)", usage = "PACKED-DECIMAL", srceLine = 32)
    protected BigInteger sXlong;
    @XmlElement(name = "SUxlong", required = true)
    @CobolElement(cobolName = "S-UXLONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 31, picture = "9(31)", usage = "PACKED-DECIMAL", srceLine = 33)
    protected BigInteger sUxlong;
    @XmlElement(name = "SDec", required = true)
    @CobolElement(cobolName = "S-DEC", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 9, fractionDigits = 2, picture = "9(7)V99", usage = "PACKED-DECIMAL", srceLine = 34)
    protected BigDecimal sDec;
    @XmlElement(name = "SFloat")
    @CobolElement(cobolName = "S-FLOAT", type = CobolType.SINGLE_FLOAT_ITEM, levelNumber = 5, usage = "COMP-1", srceLine = 35)
    protected float sFloat;
    @XmlElement(name = "SDouble")
    @CobolElement(cobolName = "S-DOUBLE", type = CobolType.DOUBLE_FLOAT_ITEM, levelNumber = 5, usage = "COMP-2", srceLine = 36)
    protected double sDouble;
    @XmlElement(name = "AString", required = true)
    @CobolElement(cobolName = "A-STRING", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 5, minOccurs = 2, maxOccurs = 2, picture = "X(4)", srceLine = 38)
    protected List<String> aString;
    @XmlElement(name = "ABinary", required = true)
    @CobolElement(cobolName = "A-BINARY", type = CobolType.ALPHANUMERIC_ITEM, levelNumber = 5, minOccurs = 2, maxOccurs = 2, picture = "X(4)", srceLine = 39)
    protected List<String> aBinary;
    @XmlElement(name = "AShort", type = Short.class)
    @CobolElement(cobolName = "A-SHORT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = true, totalDigits = 4, minOccurs = 2, maxOccurs = 2, picture = "S9(4)", usage = "BINARY", srceLine = 40)
    protected List<Short> aShort;
    @XmlElement(name = "AUshort", type = Integer.class)
    @CobolElement(cobolName = "A-USHORT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 4, minOccurs = 2, maxOccurs = 2, picture = "9(4)", usage = "BINARY", srceLine = 41)
    protected List<Integer> aUshort;
    @XmlElement(name = "AInt", type = Integer.class)
    @CobolElement(cobolName = "A-INT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = true, totalDigits = 9, minOccurs = 2, maxOccurs = 2, picture = "S9(9)", usage = "BINARY", srceLine = 42)
    protected List<Integer> aInt;
    @XmlElement(name = "AUint", type = Long.class)
    @CobolElement(cobolName = "A-UINT", type = CobolType.BINARY_ITEM, levelNumber = 5, isSigned = false, totalDigits = 9, minOccurs = 2, maxOccurs = 2, picture = "9(9)", usage = "BINARY", srceLine = 43)
    protected List<Long> aUint;
    @XmlElement(name = "ALong", type = Long.class)
    @CobolElement(cobolName = "A-LONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 18, minOccurs = 2, maxOccurs = 2, picture = "S9(18)", usage = "PACKED-DECIMAL", srceLine = 44)
    protected List<Long> aLong;
    @XmlElement(name = "AUlong", type = Long.class)
    @CobolElement(cobolName = "A-ULONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 18, minOccurs = 2, maxOccurs = 2, picture = "9(18)", usage = "PACKED-DECIMAL", srceLine = 45)
    protected List<Long> aUlong;
    @XmlElement(name = "AXlong", required = true)
    @CobolElement(cobolName = "A-XLONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = true, totalDigits = 31, minOccurs = 2, maxOccurs = 2, picture = "S9(31)", usage = "PACKED-DECIMAL", srceLine = 46)
    protected List<BigInteger> aXlong;
    @XmlElement(name = "AUxlong", required = true)
    @CobolElement(cobolName = "A-UXLONG", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 31, minOccurs = 2, maxOccurs = 2, picture = "9(31)", usage = "PACKED-DECIMAL", srceLine = 47)
    protected List<BigInteger> aUxlong;
    @XmlElement(name = "ADec", required = true)
    @CobolElement(cobolName = "A-DEC", type = CobolType.PACKED_DECIMAL_ITEM, levelNumber = 5, isSigned = false, totalDigits = 9, fractionDigits = 2, minOccurs = 2, maxOccurs = 2, picture = "9(7)V99", usage = "PACKED-DECIMAL", srceLine = 48)
    protected List<BigDecimal> aDec;
    @XmlElement(name = "AFloat", type = Float.class)
    @CobolElement(cobolName = "A-FLOAT", type = CobolType.SINGLE_FLOAT_ITEM, levelNumber = 5, minOccurs = 2, maxOccurs = 2, usage = "COMP-1", srceLine = 49)
    protected List<Float> aFloat;
    @XmlElement(name = "ADouble", type = Double.class)
    @CobolElement(cobolName = "A-DOUBLE", type = CobolType.DOUBLE_FLOAT_ITEM, levelNumber = 5, minOccurs = 2, maxOccurs = 2, usage = "COMP-2", srceLine = 50)
    protected List<Double> aDouble;

    /**
     * Gets the value of the sString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSString() {
        return sString;
    }

    /**
     * Sets the value of the sString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSString(String value) {
        this.sString = value;
    }

    public boolean isSetSString() {
        return (this.sString!= null);
    }

    /**
     * Gets the value of the sBinary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSBinary() {
        return sBinary;
    }

    /**
     * Sets the value of the sBinary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSBinary(byte[] value) {
        this.sBinary = ((byte[]) value);
    }

    public boolean isSetSBinary() {
        return (this.sBinary!= null);
    }

    /**
     * Gets the value of the sShort property.
     * 
     */
    public short getSShort() {
        return sShort;
    }

    /**
     * Sets the value of the sShort property.
     * 
     */
    public void setSShort(short value) {
        this.sShort = value;
    }

    public boolean isSetSShort() {
        return true;
    }

    /**
     * Gets the value of the sUshort property.
     * 
     */
    public int getSUshort() {
        return sUshort;
    }

    /**
     * Sets the value of the sUshort property.
     * 
     */
    public void setSUshort(int value) {
        this.sUshort = value;
    }

    public boolean isSetSUshort() {
        return true;
    }

    /**
     * Gets the value of the sInt property.
     * 
     */
    public int getSInt() {
        return sInt;
    }

    /**
     * Sets the value of the sInt property.
     * 
     */
    public void setSInt(int value) {
        this.sInt = value;
    }

    public boolean isSetSInt() {
        return true;
    }

    /**
     * Gets the value of the sUint property.
     * 
     */
    public long getSUint() {
        return sUint;
    }

    /**
     * Sets the value of the sUint property.
     * 
     */
    public void setSUint(long value) {
        this.sUint = value;
    }

    public boolean isSetSUint() {
        return true;
    }

    /**
     * Gets the value of the sLong property.
     * 
     */
    public long getSLong() {
        return sLong;
    }

    /**
     * Sets the value of the sLong property.
     * 
     */
    public void setSLong(long value) {
        this.sLong = value;
    }

    public boolean isSetSLong() {
        return true;
    }

    /**
     * Gets the value of the sUlong property.
     * 
     */
    public long getSUlong() {
        return sUlong;
    }

    /**
     * Sets the value of the sUlong property.
     * 
     */
    public void setSUlong(long value) {
        this.sUlong = value;
    }

    public boolean isSetSUlong() {
        return true;
    }

    /**
     * Gets the value of the sXlong property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSXlong() {
        return sXlong;
    }

    /**
     * Sets the value of the sXlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSXlong(BigInteger value) {
        this.sXlong = value;
    }

    public boolean isSetSXlong() {
        return (this.sXlong!= null);
    }

    /**
     * Gets the value of the sUxlong property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSUxlong() {
        return sUxlong;
    }

    /**
     * Sets the value of the sUxlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSUxlong(BigInteger value) {
        this.sUxlong = value;
    }

    public boolean isSetSUxlong() {
        return (this.sUxlong!= null);
    }

    /**
     * Gets the value of the sDec property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSDec() {
        return sDec;
    }

    /**
     * Sets the value of the sDec property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSDec(BigDecimal value) {
        this.sDec = value;
    }

    public boolean isSetSDec() {
        return (this.sDec!= null);
    }

    /**
     * Gets the value of the sFloat property.
     * 
     */
    public float getSFloat() {
        return sFloat;
    }

    /**
     * Sets the value of the sFloat property.
     * 
     */
    public void setSFloat(float value) {
        this.sFloat = value;
    }

    public boolean isSetSFloat() {
        return true;
    }

    /**
     * Gets the value of the sDouble property.
     * 
     */
    public double getSDouble() {
        return sDouble;
    }

    /**
     * Sets the value of the sDouble property.
     * 
     */
    public void setSDouble(double value) {
        this.sDouble = value;
    }

    public boolean isSetSDouble() {
        return true;
    }

    /**
     * Gets the value of the aString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAString() {
        if (aString == null) {
            aString = new ArrayList<String>();
        }
        return this.aString;
    }

    public boolean isSetAString() {
        return ((this.aString!= null)&&(!this.aString.isEmpty()));
    }

    public void unsetAString() {
        this.aString = null;
    }

    /**
     * Gets the value of the aBinary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aBinary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getABinary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getABinary() {
        if (aBinary == null) {
            aBinary = new ArrayList<String>();
        }
        return this.aBinary;
    }

    public boolean isSetABinary() {
        return ((this.aBinary!= null)&&(!this.aBinary.isEmpty()));
    }

    public void unsetABinary() {
        this.aBinary = null;
    }

    /**
     * Gets the value of the aShort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aShort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAShort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Short }
     * 
     * 
     */
    public List<Short> getAShort() {
        if (aShort == null) {
            aShort = new ArrayList<Short>();
        }
        return this.aShort;
    }

    public boolean isSetAShort() {
        return ((this.aShort!= null)&&(!this.aShort.isEmpty()));
    }

    public void unsetAShort() {
        this.aShort = null;
    }

    /**
     * Gets the value of the aUshort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aUshort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAUshort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getAUshort() {
        if (aUshort == null) {
            aUshort = new ArrayList<Integer>();
        }
        return this.aUshort;
    }

    public boolean isSetAUshort() {
        return ((this.aUshort!= null)&&(!this.aUshort.isEmpty()));
    }

    public void unsetAUshort() {
        this.aUshort = null;
    }

    /**
     * Gets the value of the aInt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aInt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAInt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getAInt() {
        if (aInt == null) {
            aInt = new ArrayList<Integer>();
        }
        return this.aInt;
    }

    public boolean isSetAInt() {
        return ((this.aInt!= null)&&(!this.aInt.isEmpty()));
    }

    public void unsetAInt() {
        this.aInt = null;
    }

    /**
     * Gets the value of the aUint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aUint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAUint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getAUint() {
        if (aUint == null) {
            aUint = new ArrayList<Long>();
        }
        return this.aUint;
    }

    public boolean isSetAUint() {
        return ((this.aUint!= null)&&(!this.aUint.isEmpty()));
    }

    public void unsetAUint() {
        this.aUint = null;
    }

    /**
     * Gets the value of the aLong property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aLong property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getALong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getALong() {
        if (aLong == null) {
            aLong = new ArrayList<Long>();
        }
        return this.aLong;
    }

    public boolean isSetALong() {
        return ((this.aLong!= null)&&(!this.aLong.isEmpty()));
    }

    public void unsetALong() {
        this.aLong = null;
    }

    /**
     * Gets the value of the aUlong property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aUlong property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAUlong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getAUlong() {
        if (aUlong == null) {
            aUlong = new ArrayList<Long>();
        }
        return this.aUlong;
    }

    public boolean isSetAUlong() {
        return ((this.aUlong!= null)&&(!this.aUlong.isEmpty()));
    }

    public void unsetAUlong() {
        this.aUlong = null;
    }

    /**
     * Gets the value of the aXlong property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aXlong property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAXlong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getAXlong() {
        if (aXlong == null) {
            aXlong = new ArrayList<BigInteger>();
        }
        return this.aXlong;
    }

    public boolean isSetAXlong() {
        return ((this.aXlong!= null)&&(!this.aXlong.isEmpty()));
    }

    public void unsetAXlong() {
        this.aXlong = null;
    }

    /**
     * Gets the value of the aUxlong property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aUxlong property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAUxlong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getAUxlong() {
        if (aUxlong == null) {
            aUxlong = new ArrayList<BigInteger>();
        }
        return this.aUxlong;
    }

    public boolean isSetAUxlong() {
        return ((this.aUxlong!= null)&&(!this.aUxlong.isEmpty()));
    }

    public void unsetAUxlong() {
        this.aUxlong = null;
    }

    /**
     * Gets the value of the aDec property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aDec property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getADec().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getADec() {
        if (aDec == null) {
            aDec = new ArrayList<BigDecimal>();
        }
        return this.aDec;
    }

    public boolean isSetADec() {
        return ((this.aDec!= null)&&(!this.aDec.isEmpty()));
    }

    public void unsetADec() {
        this.aDec = null;
    }

    /**
     * Gets the value of the aFloat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aFloat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAFloat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getAFloat() {
        if (aFloat == null) {
            aFloat = new ArrayList<Float>();
        }
        return this.aFloat;
    }

    public boolean isSetAFloat() {
        return ((this.aFloat!= null)&&(!this.aFloat.isEmpty()));
    }

    public void unsetAFloat() {
        this.aFloat = null;
    }

    /**
     * Gets the value of the aDouble property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aDouble property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getADouble().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getADouble() {
        if (aDouble == null) {
            aDouble = new ArrayList<Double>();
        }
        return this.aDouble;
    }

    public boolean isSetADouble() {
        return ((this.aDouble!= null)&&(!this.aDouble.isEmpty()));
    }

    public void unsetADouble() {
        this.aDouble = null;
    }

}
