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

import com.legstar.test.coxb.ListssdoCases;
import com.legstar.test.coxb.listssdo.Dfhcommarea;

/**
 * Test LISTSSDO.
 *
 */
public class UnmarshalListssdoTest extends AbstractTestUnmarshal {
    /**
     * Unmarshal Listssdo.
     */
    public void testListssdo() {
        Dfhcommarea dfhcommarea = (Dfhcommarea) convert(
                ListssdoCases.getFactory(),
                ListssdoCases.getHostBytesHex(),
                ListssdoCases.getJavaObject());
        ListssdoCases.checkJavaObject(dfhcommarea);
    }

}
