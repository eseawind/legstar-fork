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
package com.legstar.eclipse.plugin.schemagen;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID =
        "com.legstar.eclipse.plugin.schemagen";

    /** The shared instance. */
    private static Activator mPlugin;

    /**
     * The constructor.
     */
    public Activator() {
    }

    /**
     * {@inheritDoc}.
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(
     * org.osgi.framework.BundleContext)
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        mPlugin = this;
    }

    /**
     * {@inheritDoc}.
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(
     * org.osgi.framework.BundleContext)
     */
    public void stop(final BundleContext context) throws Exception {
        mPlugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * @return the shared instance
     */
    public static Activator getDefault() {
        return mPlugin;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path.
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(
            final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
