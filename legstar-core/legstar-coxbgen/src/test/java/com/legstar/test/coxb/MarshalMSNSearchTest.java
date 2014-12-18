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
package com.legstar.test.coxb;

import java.io.StringReader;

import junit.framework.TestCase;

import com.legstar.coxb.host.HostData;
import com.legstar.test.coxb.MSNSearch.bind.SearchJavaToHostTransformer;
import com.legstar.test.coxb.MSNSearch.bind.SearchResponseJavaToHostTransformer;
import com.legstar.test.coxb.MSNSearch.bind.SearchResponseJsonToHostTransformer;

/**
 * Marshal MSNSearch.
 * 
 */
public class MarshalMSNSearchTest extends TestCase {

    /**
     * Transform java data object and test host data result.
     * 
     * @throws Exception if transforming fails
     */
    public void testSearchJavaToHostTransformer() throws Exception {

        SearchJavaToHostTransformer transformer = new SearchJavaToHostTransformer();
        assertEquals(MSNSearchCases.getHostBytesHexRequest(),
                HostData.toHexString(transformer.transform(MSNSearchCases
                        .getJaxbObjectRequest())));

    }

    /**
     * Transform java data object and test host data result.
     * 
     * @throws Exception if transforming fails
     */
    public void testSearchResponseJavaToHostTransformer() throws Exception {

        SearchResponseJavaToHostTransformer transformer = new SearchResponseJavaToHostTransformer();
        assertEquals(MSNSearchCases.getHostBytesHexResponse(),
                HostData.toHexString(transformer.transform(MSNSearchCases
                        .getJaxbObjectResponse())));
    }

    /**
     * Transform JSON and test host data result.
     * 
     * @throws Exception if transforming fails
     */
    public void testSearchResponseJsonToHostTransformer() throws Exception {

        SearchResponseJsonToHostTransformer transformer = new SearchResponseJsonToHostTransformer();
        assertEquals(MSNSearchCases.getHostBytesHexResponse(),
                HostData
                        .toHexString(transformer.transform(
                                new StringReader(MSNSearchCases
                                        .getSearchResponseJson()))));
    }
}
