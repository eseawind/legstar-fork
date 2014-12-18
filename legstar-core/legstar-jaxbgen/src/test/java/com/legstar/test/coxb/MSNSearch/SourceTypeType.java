
package com.legstar.test.coxb.MSNSearch;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Web"/>
 *     &lt;enumeration value="News"/>
 *     &lt;enumeration value="Ads"/>
 *     &lt;enumeration value="InlineAnswers"/>
 *     &lt;enumeration value="PhoneBook"/>
 *     &lt;enumeration value="WordBreaker"/>
 *     &lt;enumeration value="Spelling"/>
 *     &lt;enumeration value="QueryLocation"/>
 *     &lt;enumeration value="Image"/>
 *     &lt;enumeration value="Video"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SourceType")
@XmlEnum
public enum SourceTypeType {

    @XmlEnumValue("Web")
    WEB("Web"),
    @XmlEnumValue("News")
    NEWS("News"),
    @XmlEnumValue("Ads")
    ADS("Ads"),
    @XmlEnumValue("InlineAnswers")
    INLINE_ANSWERS("InlineAnswers"),
    @XmlEnumValue("PhoneBook")
    PHONE_BOOK("PhoneBook"),
    @XmlEnumValue("WordBreaker")
    WORD_BREAKER("WordBreaker"),
    @XmlEnumValue("Spelling")
    SPELLING("Spelling"),
    @XmlEnumValue("QueryLocation")
    QUERY_LOCATION("QueryLocation"),
    @XmlEnumValue("Image")
    IMAGE("Image"),
    @XmlEnumValue("Video")
    VIDEO("Video");
    private final String value;

    SourceTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SourceTypeType fromValue(String v) {
        for (SourceTypeType c: SourceTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
