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
package com.legstar.clients.tests;

import com.legstar.test.cixs.redbotha.RedbothaFault;
import com.legstar.test.cixs.redbotha.RedbothaPort;
import com.legstar.test.cixs.redbotha.RedbothaRequest;
import com.legstar.test.cixs.redbotha.RedbothaResponse;
import com.legstar.test.cixs.redbotha.RedbothaService;
import com.legstar.test.coxb.redbotha.Dfhcommarea;
import com.legstar.test.coxb.redbotha.Filler22;

public class ClientredbothaITCase extends AbstractITCase {

    public void testClientAlternative1() throws RedbothaFault {
        com.legstar.test.cixs.redbotha.ObjectFactory wsOF = new com.legstar.test.cixs.redbotha.ObjectFactory();
        com.legstar.test.coxb.redbotha.ObjectFactory obOF = new com.legstar.test.coxb.redbotha.ObjectFactory();
        RedbothaPort port = new RedbothaService().getRedbothaPort();
        RedbothaRequest req = wsOF.createRedbothaRequest();
        Dfhcommarea dfhcommarea = obOF.createDfhcommarea();
        req.setDfhcommarea(dfhcommarea);

        Filler22 filler22 = obOF.createFiller22();
        filler22.setCLeftByte("A");
        filler22.setCRightByte("B");
        dfhcommarea.setFiller22(filler22);

        RedbothaResponse resp = port.redbotha(req, null);
        Dfhcommarea dfhcommareaResp = resp.getDfhcommarea();

        /* The effect of the choice selector is to produce both alternatives */
        assertEquals(49602, dfhcommareaResp.getCNumeric().intValue());
        assertEquals("A", dfhcommareaResp.getFiller22().getCLeftByte());
        assertEquals("B", dfhcommareaResp.getFiller22().getCRightByte());
    }

    public void testClientAlternative2() throws RedbothaFault {
        com.legstar.test.cixs.redbotha.ObjectFactory wsOF = new com.legstar.test.cixs.redbotha.ObjectFactory();
        com.legstar.test.coxb.redbotha.ObjectFactory obOF = new com.legstar.test.coxb.redbotha.ObjectFactory();
        RedbothaPort port = new RedbothaService().getRedbothaPort();
        RedbothaRequest req = wsOF.createRedbothaRequest();
        Dfhcommarea dfhcommarea = obOF.createDfhcommarea();
        req.setDfhcommarea(dfhcommarea);

        dfhcommarea.setCNumeric(55256);

        RedbothaResponse resp = port.redbotha(req, null);
        Dfhcommarea dfhcommareaResp = resp.getDfhcommarea();

        /* The effect of the choice selector is to produce both alternatives */
        assertEquals(55256, dfhcommareaResp.getCNumeric().intValue());
        assertEquals("P", dfhcommareaResp.getFiller22().getCLeftByte());
        assertEquals("Q", dfhcommareaResp.getFiller22().getCRightByte());
    }
}
