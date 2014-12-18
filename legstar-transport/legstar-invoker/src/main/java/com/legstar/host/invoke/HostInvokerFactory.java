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
package com.legstar.host.invoke;

import com.legstar.config.LegStarConfigurationException;
import com.legstar.config.commons.LegStarConfigCommons;
import com.legstar.host.access.HostAccessStrategy;
import com.legstar.host.access.HostAccessStrategyException;
import com.legstar.host.access.HostAccessStrategyFactory;
import com.legstar.host.invoke.model.HostProgram;
import com.legstar.host.invoke.model.HostProgramException;
import com.legstar.messaging.HostEndpoint;
import com.legstar.messaging.LegStarAddress;

/**
 * A factory providing a host invoke capability. Based on the target host
 * program attributes, the factory selects an appropriate invoker.
 */
public final class HostInvokerFactory {

    /** The LegStar configuration. */
    private static LegStarConfigCommons _config;

    /**
     * This factory is a utility class.
     */
    private HostInvokerFactory() {

    }

    /**
     * An Invoker is constructed from a configuration file, for a particular
     * host address and target host program.
     * 
     * @param generalConfigFileName an XML configuration file name
     * @param address the host address
     * @param hostProgram the host program bean file
     * @return a Host invoke implementation
     * @throws HostInvokerException in construction fails
     */
    public static HostInvoker createHostInvoker(
            final String generalConfigFileName, final LegStarAddress address,
            final HostProgram hostProgram) throws HostInvokerException {

        try {
            /* Load the LegStar configuration, if necessary */
            if (_config == null) {
                _config = new LegStarConfigCommons(generalConfigFileName);
            }

            /*
             * Load endpoint configuration specified in requested address or the
             * default one if address is empty.
             */
            HostEndpoint endpoint = _config.getHostEndpoint(address);

            /*
             * Create a complete address by merging requested address attributes
             * with the endpoint configuration ones. For instance if requested
             * address does not specify credentials, we will pick up the default
             * credentials from the configuration.
             */
            LegStarAddress completeAddress = new LegStarAddress(address,
                    endpoint);

            /* Load a host access strategy */
            HostAccessStrategy hostAccessStrategy;
            hostAccessStrategy = HostAccessStrategyFactory
                    .createAccessStrategy(endpoint);

            /* If a Channel is specified, this is a request for LINK CHANNEL */
            String channelName = hostProgram.getChannelName();
            if (channelName != null && channelName.length() > 0) {
                return new ContainerInvoker(hostAccessStrategy,
                        completeAddress, hostProgram);
            }

            /* The default is a commarea invoke */
            return new CommareaInvoker(hostAccessStrategy, completeAddress,
                    hostProgram);
        } catch (HostAccessStrategyException e) {
            throw new HostInvokerException(e);
        } catch (LegStarConfigurationException e) {
            throw new HostInvokerException(e);
        }
    }

    /**
     * @deprecated An Invoker is constructed from a configuration file, for a
     *             particular host address and target host program.
     * @param generalConfigFileName an XML configuration file name
     * @param address the host address
     * @param cicsProgramFileName the host program attributes properties file
     * @return a Host invoke implementation
     * @throws HostInvokerException in construction fails
     */
    public static HostInvoker createHostInvoker(
            final String generalConfigFileName, final LegStarAddress address,
            final String cicsProgramFileName) throws HostInvokerException {

        try {
            return createHostInvoker(generalConfigFileName, address,
                    new HostProgramProperties(cicsProgramFileName));
        } catch (HostProgramException e) {
            throw new HostInvokerException(e);
        }
    }

}
