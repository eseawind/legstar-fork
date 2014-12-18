package com.legstar.test.coxb.lsfileac.bind;

import com.legstar.coxb.CobolContext;
import com.legstar.coxb.transform.AbstractXmlToHostTransformer;
import com.legstar.coxb.transform.HostTransformException;

/**
 * Transforms XML to mainframe data.
 * <p/>
 * This is a typical use of this class:
 * <pre>
 *  StringReader reader = new StringReader("<someXml>...</someXml>");
 *  ReplyStatusXmlToHostTransformer transformer = new ReplyStatusXmlToHostTransformer();
 *  byte[] hostByteArray = transformer.transform(new StreamSource(reader));
 * </pre>
 *
 */
public class ReplyStatusXmlToHostTransformer extends AbstractXmlToHostTransformer {

    
    /**
     * Create a XML to Host transformer using a Java to Host transformer.
     * @throws HostTransformException if transformer cannot be created
     */
    public ReplyStatusXmlToHostTransformer() throws HostTransformException {
        super(new ReplyStatusJavaToHostTransformer());
    }
    
    /**
     * Create an XML to Host transformer using a specific COBOL parameters set.
     * @param cobolContext the COBOL parameters set.
     * @throws HostTransformException if transformer cannot be created
     */
    public ReplyStatusXmlToHostTransformer(
            final CobolContext cobolContext) throws HostTransformException {
        super(new ReplyStatusJavaToHostTransformer(cobolContext));
    }

    /**
     * Create an XML to Host transformer using a specific host character set while
     * other COBOL parameters are set by default.
     * @param hostCharset the host character set
     * @throws HostTransformException if transformer cannot be created
     */
    public ReplyStatusXmlToHostTransformer(
            final String hostCharset) throws HostTransformException {
        super(new ReplyStatusJavaToHostTransformer(hostCharset));
    }
    
}
