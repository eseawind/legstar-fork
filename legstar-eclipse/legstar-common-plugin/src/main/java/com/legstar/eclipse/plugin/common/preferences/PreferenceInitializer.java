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
package com.legstar.eclipse.plugin.common.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.legstar.eclipse.plugin.common.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     * @see org.eclipse.core.runtime.preferences.
     * AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.ANT_SCRIPTS_FOLDER,
        "ant");
        store.setDefault(PreferenceConstants.HOST_CHARSET,
        "IBM01140");
    }

    /**
     * Schema generator is a sub product of LegStar. If the full product is
     * installed then the location should be that of the full product.
     * Otherwise, the LEGSTAR_HOME environment variable might point to the
     * location.
     * @return the default location
     */
    public String getDefaultProductLocation() {
        String value = System.getenv("LEGSTAR_HOME");
        if (value == null) {
            return "";
        }
        return value;
    }

}
