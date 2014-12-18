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
package com.legstar.messaging;


/**
 * Abstract factory shields users from the transport layer.
 * <p/>
 * This plays both the role of an endpoint factory via the createEndpoint method
 * and a connection factory via the createConnection method. These should
 * probably be 2 separate classes but that would mean yet another configuration
 * parameter.
 */
public interface ConnectionFactory {

    /**
     * @return a new instance of the transport specific endpoint
     */
    HostEndpoint createEndpoint();

    /**
     * Create a new host connection. This method creates a connection object
     * but whether or not a physical connection is established is left to
     * the actual implementation.
     * 
     * @param connectionID an identifier for this connection
     * @param address the target address parameters (user/password, etc...)
     * @param endpoint the target host endpoint
     * @return the new host connection
     * @throws ConnectionException if failed to create connection
     */
    LegStarConnection createConnection(
            String connectionID,
            LegStarAddress address,
            HostEndpoint endpoint) throws ConnectionException;
    
}
