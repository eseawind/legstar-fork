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
package com.legstar.test.cixs;

import com.legstar.cixs.gen.model.CixsOperation;

/**
 * Mapping cases for LSFILEAE. A simple commarea-driven program.
 * 
 */
public class LsfileaeOperationCases extends AbstractOperationCases {

    /**
     * @param serviceName the service name
     * @param operationPackageName the operation classes package name
     * @return an operation corresponding to a Web Service operation.
     */
    public static CixsOperation getOperation(final String serviceName,
            final String operationPackageName) {
        CixsOperation cixsOperation = new CixsOperation();
        cixsOperation.setName("lsfileae");
        cixsOperation.setCicsProgramName("LSFILEAE");
        cixsOperation.setPackageName(operationPackageName);
        cixsOperation.addInput(createCixsStructure(serviceName, "Dfhcommarea",
                null, false));
        cixsOperation.addOutput(createCixsStructure(serviceName, "Dfhcommarea",
                null, false));
        return cixsOperation;
    }

}
