
package com.legstar.test.cixs.doublmix;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.legstar.test.cixs.doublmix package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DoublmixResponse_QNAME = new QName("http://cixs.test.legstar.com/doublmix", "DoublmixResponse");
    private final static QName _DoublmixRequest_QNAME = new QName("http://cixs.test.legstar.com/doublmix", "DoublmixRequest");
    private final static QName _DoublmixHostHeader_QNAME = new QName("http://cixs.test.legstar.com/doublmix", "DoublmixHostHeader");
    private final static QName _DoublmixFaultInfo_QNAME = new QName("http://cixs.test.legstar.com/doublmix", "DoublmixFaultInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.legstar.test.cixs.doublmix
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoublmixHostHeader }
     * 
     */
    public DoublmixHostHeader createDoublmixHostHeader() {
        return new DoublmixHostHeader();
    }

    /**
     * Create an instance of {@link DoublmixResponse }
     * 
     */
    public DoublmixResponse createDoublmixResponse() {
        return new DoublmixResponse();
    }

    /**
     * Create an instance of {@link DoublmixRequest }
     * 
     */
    public DoublmixRequest createDoublmixRequest() {
        return new DoublmixRequest();
    }

    /**
     * Create an instance of {@link DoublmixFaultInfo }
     * 
     */
    public DoublmixFaultInfo createDoublmixFaultInfo() {
        return new DoublmixFaultInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoublmixResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cixs.test.legstar.com/doublmix", name = "DoublmixResponse")
    public JAXBElement<DoublmixResponse> createDoublmixResponse(DoublmixResponse value) {
        return new JAXBElement<DoublmixResponse>(_DoublmixResponse_QNAME, DoublmixResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoublmixRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cixs.test.legstar.com/doublmix", name = "DoublmixRequest")
    public JAXBElement<DoublmixRequest> createDoublmixRequest(DoublmixRequest value) {
        return new JAXBElement<DoublmixRequest>(_DoublmixRequest_QNAME, DoublmixRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoublmixHostHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cixs.test.legstar.com/doublmix", name = "DoublmixHostHeader")
    public JAXBElement<DoublmixHostHeader> createDoublmixHostHeader(DoublmixHostHeader value) {
        return new JAXBElement<DoublmixHostHeader>(_DoublmixHostHeader_QNAME, DoublmixHostHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoublmixFaultInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cixs.test.legstar.com/doublmix", name = "DoublmixFaultInfo")
    public JAXBElement<DoublmixFaultInfo> createDoublmixFaultInfo(DoublmixFaultInfo value) {
        return new JAXBElement<DoublmixFaultInfo>(_DoublmixFaultInfo_QNAME, DoublmixFaultInfo.class, null, value);
    }

}
