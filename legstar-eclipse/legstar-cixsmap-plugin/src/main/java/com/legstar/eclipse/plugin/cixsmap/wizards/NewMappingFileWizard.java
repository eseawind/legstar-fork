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
package com.legstar.eclipse.plugin.cixsmap.wizards;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.eclipse.osgi.util.NLS;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.ide.IDE;

import com.legstar.cixs.gen.model.CixsMappingModel;
import com.legstar.eclipse.plugin.cixsmap.Activator;
import com.legstar.eclipse.plugin.cixsmap.Messages;
import com.legstar.eclipse.plugin.common.wizards.AbstractWizard;

/**
 * Legacy new mapping file wizard.
 * Mapping files describe how legacy programs map to Java/Web Services
 * operations.
 * The mapping file has an extension of ".cixs" and is registered with
 * a multi-page editor.
 */

public class NewMappingFileWizard extends AbstractWizard implements INewWizard {

    /** What we are trying to generate. */
    public static final String GENERATION_SUBJECT = "Operation mapping";

    /** This wizard only has one page. */
    private NewMappingFileWizardPage _page;

    /** Current workspace selection. */
    private IStructuredSelection _selection;

    /**
     * Constructor for NewMappingFileWizard.
     */
    public NewMappingFileWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * Adding the page to the wizard.
     */

    public void addPages() {
        _page = new NewMappingFileWizardPage(_selection);
        addPage(_page);
    }

    /** {@inheritDoc} */
    @Override
    public IRunnableWithProgress getWizardRunnable()
            throws InvocationTargetException {
        final String containerName = _page.getContainerName();
        final String fileName = _page.getFileName();
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(
                    final IProgressMonitor monitor)
                    throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        return op;
    }

    /**
     * The worker method. It will find the container, create the
     * file if missing or just replace its contents, and open
     * the editor on the newly created file.
     * 
     * @param containerName where the file should go
     * @param fileName the mapping file name
     * @param monitor to follow progress
     * @throws CoreException if something goes wrong
     */

    private void doFinish(
            final String containerName,
            final String fileName,
            final IProgressMonitor monitor)
            throws CoreException {
        // create a sample file
        monitor.beginTask(NLS.bind(
                Messages.editor_creating_file_task_label, fileName), 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException("Container \"" + containerName
                    + "\" does not exist.");
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName));
        final String name =
                new Path(fileName).removeFileExtension().toOSString();
        try {
            InputStream stream = openContentStream(name);
            if (file.exists()) {
                file.setContents(stream, true, true, monitor);
            } else {
                file.create(stream, true, monitor);
            }
            stream.close();
        } catch (IOException e) {
            throwCoreException(e);
        }
        monitor.worked(1);
        monitor.setTaskName(NLS.bind(
                Messages.editor_opening_file_task_label, fileName));
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                                .getActivePage();
                try {
                    IDE.openEditor(page, file, true);
                } catch (PartInitException e) {
                    logCoreException(e, Activator.PLUGIN_ID);
                }
            }
        });
        monitor.worked(1);
    }

    /**
     * We will initialize file contents with an empty mapping.
     * 
     * @param serviceName the service name
     * @return an input stream
     */
    private InputStream openContentStream(final String serviceName) {
        CixsMappingModel model = new CixsMappingModel();
        model.setName(serviceName);
        String contents = model.serialize();
        return new ByteArrayInputStream(contents.getBytes());
    }

    /**
     * We will accept the selection in the workbench to see if
     * we can initialize from it. {@inheritDoc}
     */
    public void init(
            final IWorkbench workbench, final IStructuredSelection selection) {
        _selection = selection;
    }

    /** {@inheritDoc} */
    @Override
    public String getGenerationSubject() {
        return GENERATION_SUBJECT;
    }

    /** {@inheritDoc} */
    @Override
    public Properties getPersistProperties() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getPluginId() {
        return Activator.PLUGIN_ID;
    }

}
