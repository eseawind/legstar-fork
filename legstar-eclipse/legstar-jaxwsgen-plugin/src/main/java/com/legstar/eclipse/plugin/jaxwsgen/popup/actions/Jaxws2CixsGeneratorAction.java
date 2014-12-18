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
package com.legstar.eclipse.plugin.jaxwsgen.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.legstar.eclipse.plugin.cixscom.popup.actions.AbstractGeneratorAction;
import com.legstar.eclipse.plugin.jaxwsgen.wizards
.Jaxws2CixsGeneratorWizardLauncher;

/**
 * This action becomes available when a LegStar mapping file is selected.
 */
public class Jaxws2CixsGeneratorAction extends AbstractGeneratorAction {

    /** {@inheritDoc} */
    @Override
    public void startWizard(final IFile mappingFile) throws CoreException {
        new Jaxws2CixsGeneratorWizardLauncher().startGenerationWizard(
                mappingFile);
    }

}
