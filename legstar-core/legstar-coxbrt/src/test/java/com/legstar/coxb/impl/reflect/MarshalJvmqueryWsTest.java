/*******************************************************************************
 * Copyright (c) 2011 LegSem.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     LegSem - initial API and implementation
 ******************************************************************************/
package com.legstar.coxb.impl.reflect;

import com.legstar.test.coxb.JvmqueryWsCases;

/**
 * Test JvmqueryWs.
 *
 */
public class MarshalJvmqueryWsTest extends AbstractTestMarshal {

    /**
     * Convert and check result.
     */
    public void testJvmqueryWs() {
        convertAndCheck(
                JvmqueryWsCases.getFactory(),
                JvmqueryWsCases.getJavaObject(),
                JvmqueryWsCases.getHostBytesHex());
    }

}
