/*******************************************************************************
 * Copyright (c) 2010 LegSem.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     LegSem - initial API and implementation
 ******************************************************************************/
package com.legstar.mq.mqcih.bind;

import java.io.Writer;
import javax.xml.transform.Source;

import com.legstar.coxb.transform.AbstractXmlTransformers;
import com.legstar.coxb.transform.HostTransformException;

/**
 * XML Transformers provider for Mqcih.
 * 
 */
public class MqcihXmlTransformers extends AbstractXmlTransformers {

    /**
     * Create a set of directional transformers.
     * 
     * @throws HostTransformException if transformer cannot be created
     */
    public MqcihXmlTransformers() throws HostTransformException {
        super(new MqcihXmlToHostTransformer(),
                new MqcihHostToXmlTransformer());
    }

    /**
     * Transforms XML to host data with a specific host character set.
     * 
     * @param source the XML Source to unmarshal XML data from (such as
     *            SAXSource, DOMSource, and StreamSource)
     * @param hostCharset the host character set
     * @return a byte array with host data
     * @throws HostTransformException if transformation fails
     */
    public byte[] toHost(final Source source, final String hostCharset)
            throws HostTransformException {
        return getXmlToHost().transform(source, hostCharset);
    }

    /**
     * Transforms XML to host data.
     * 
     * @param source the XML Source to unmarshal XML data from (such as
     *            SAXSource, DOMSource, and StreamSource)
     * @return a byte array with host data
     * @throws HostTransformException if transformation fails
     */
    public byte[] toHost(final Source source)
            throws HostTransformException {
        return getXmlToHost().transform(source);
    }

    /**
     * Transforms host data to XML with a specific host character set.
     * 
     * @param hostData a byte array containing host data
     * @param writer XML will be sent to this writer.
     * @param hostCharset the host character set
     * @throws HostTransformException if transformation fails
     */
    public void toXml(final byte[] hostData, final Writer writer,
            final String hostCharset)
            throws HostTransformException {
        getHostToXml().transform(hostData, writer, hostCharset);
    }

    /**
     * Transforms host data to XML.
     * 
     * @param hostData a byte array containing host data
     * @param writer XML will be sent to this writer.
     * @throws HostTransformException if transformation fails
     */
    public void toXml(final byte[] hostData, final Writer writer)
            throws HostTransformException {
        getHostToXml().transform(hostData, writer);
    }

}
