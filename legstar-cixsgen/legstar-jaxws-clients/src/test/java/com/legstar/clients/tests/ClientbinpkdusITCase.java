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

import java.math.BigInteger;

import com.legstar.test.cixs.binpkdus.BinpkdusFault;
import com.legstar.test.cixs.binpkdus.BinpkdusPort;
import com.legstar.test.cixs.binpkdus.BinpkdusRequest;
import com.legstar.test.cixs.binpkdus.BinpkdusResponse;
import com.legstar.test.cixs.binpkdus.BinpkdusService;
import com.legstar.test.coxb.binpkdus.Dfhcommarea;
import com.legstar.test.coxb.binpkdus.LsCompat;
import com.legstar.test.coxb.binpkdus.LsExtend;
import com.legstar.test.coxb.binpkdus.LsUnsignedPackedDecimal;

public class ClientbinpkdusITCase extends AbstractITCase {

    public void testClient() throws BinpkdusFault {
        com.legstar.test.cixs.binpkdus.ObjectFactory wsOF = new com.legstar.test.cixs.binpkdus.ObjectFactory();
        com.legstar.test.coxb.binpkdus.ObjectFactory obOF = new com.legstar.test.coxb.binpkdus.ObjectFactory();
        BinpkdusPort port = new BinpkdusService().getBinpkdusPort();
        BinpkdusRequest req = wsOF.createBinpkdusRequest();
        Dfhcommarea dfhcommarea = obOF.createDfhcommarea();
        req.setDfhcommarea(dfhcommarea);

        LsCompat lsCompat = obOF.createLsCompat();
        lsCompat.setLsP9X1(3);
        lsCompat.setLsP9X18(123456789012345678l);
        lsCompat.setLsP9X1Null(0);
        lsCompat.setLsP9X2(12);
        lsCompat.setLsP9X7(32769);

        LsExtend lsExtend = obOF.createLsExtend();
        lsExtend.setLsP9X19(new BigInteger("1234567890123456789"));
        lsExtend.setLsP9X31(new BigInteger("1234567890123456789012345678901"));

        LsUnsignedPackedDecimal lsUnsignedPackedDecimal = obOF
                .createLsUnsignedPackedDecimal();
        lsUnsignedPackedDecimal.setLsCompat(lsCompat);
        lsUnsignedPackedDecimal.setLsExtend(lsExtend);
        dfhcommarea.setLsUnsignedPackedDecimal(lsUnsignedPackedDecimal);

        BinpkdusResponse resp = port.binpkdus(req, null);
        Dfhcommarea dfhcommareaResp = resp.getDfhcommarea();

        assertEquals(3, dfhcommareaResp.getLsUnsignedPackedDecimal()
                .getLsCompat().getLsP9X1());
        assertEquals(123456789012345678l, dfhcommareaResp
                .getLsUnsignedPackedDecimal().getLsCompat().getLsP9X18());
        assertEquals(0, dfhcommareaResp.getLsUnsignedPackedDecimal()
                .getLsCompat().getLsP9X1Null());
        assertEquals(12, dfhcommareaResp.getLsUnsignedPackedDecimal()
                .getLsCompat().getLsP9X2());
        assertEquals(32769, dfhcommareaResp.getLsUnsignedPackedDecimal()
                .getLsCompat().getLsP9X7());
        assertEquals(new BigInteger("1234567890123456789"), dfhcommareaResp
                .getLsUnsignedPackedDecimal().getLsExtend().getLsP9X19());
        assertEquals(new BigInteger("1234567890123456789012345678901"),
                dfhcommareaResp.getLsUnsignedPackedDecimal().getLsExtend()
                        .getLsP9X31());
    }

}
