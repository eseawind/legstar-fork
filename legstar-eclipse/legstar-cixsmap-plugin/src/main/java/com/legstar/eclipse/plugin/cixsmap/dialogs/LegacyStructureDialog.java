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
package com.legstar.eclipse.plugin.cixsmap.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.legstar.cixs.gen.model.CixsStructure;
import com.legstar.cobol.gen.CobolNameResolver;
import com.legstar.eclipse.plugin.cixsmap.Activator;
import com.legstar.eclipse.plugin.cixsmap.Messages;
import com.legstar.eclipse.plugin.common.dialogs.AbstractDialog;

/**
 * Dialog used to capture a legacy Web service operation attributes.
 * 
 * @author Fady Moussallam
 * 
 */
public class LegacyStructureDialog extends AbstractDialog {

    /** Last segment of coxb binding classes package. */
    public static final String BIND_FRAG = "bind";

    /** Coxb binding classes prefix. */
    private static final String BIND_SUFFIX = "Binding";

    /** Map package names to their jdt fragment. */
    private Map < String, IPackageFragment > mPackageMap;

    /** The structure element being edited. */
    private CixsStructure mStructure;

    /** Dialog widgets. */
    /** Jaxb package combo. */
    private Combo mJaxbPackageCombo = null;

    /** Jaxb type list. */
    private List mJaxbTypeList = null;

    /** COBOL Root item Text. */
    private Text mCobolRootDataItemNameText = null;

    /** CICS Container Text. */
    private Text mCicsContainerText = null;

    /** The legacy mapping file. */
    private IFile mMappingFile;

    /** Helper to suggest COBOL names from Java names. */
    private CobolNameResolver mCobolNameResolver;

    /**
     * Constructor for structure dialog.
     * 
     * @param pluginID the current plugin ID
     * @param parentShell the parent shell
     * @param mappingFile the legacy mapping file
     * @param structure the structure being edited
     */
    public LegacyStructureDialog(final String pluginID,
            final Shell parentShell, final IFile mappingFile,
            final CixsStructure structure) {
        super(parentShell, pluginID);
        mMappingFile = mappingFile;
        mStructure = structure;
    }

    /** {@inheritDoc} */
    protected final Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        try {
            mCobolNameResolver = new CobolNameResolver();
            initialize(composite);
        } catch (CoreException e) {
            errorDialog(Messages.structure_mapping_error_dialog_title,
                    NLS.bind(Messages.invalid_java_project_msg, mMappingFile
                            .getProject().getName(), e.getMessage()));
        }
        return composite;
    }

    /**
     * {@inheritDoc} We override this method because we want to perform
     * validation immediately when the dialog is created (the project might not
     * contain binding classes and therefore there will be no JAXB types to
     * select) Since validation might disable the OK button, we need to wait
     * until this button is created.
     */
    protected final Control createButtonBar(final Composite parent) {
        Control control = super.createButtonBar(parent);
        dialogChanged();
        return control;
    }

    /**
     * Create dialog widgets.
     * 
     * @param parent the parent composite
     * @throws CoreException if creation fails
     */
    private void initialize(final Composite parent) throws CoreException {

        Composite area = new Composite(parent, SWT.NULL);
        GridLayout gridLayout = new GridLayout(2, false);
        area.setLayout(gridLayout);

        initJaxbPackageCombo(area);
        initJaxbTypeCombo(area);

        createLabel(area, Messages.structure_cobol_root_label + ':');
        mCobolRootDataItemNameText = createText(area,
                mStructure.getCobolRootDataItemName(), -1);
        mCobolRootDataItemNameText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });

        createLabel(area, Messages.structure_container_label + ':');
        mCicsContainerText = createText(area, mStructure.getCicsContainer(), -1);
        mCicsContainerText.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                dialogChanged();
            }
        });

        /*
         * We assume that the binding classes are in the same project as the
         * mapping file.
         */
        mPackageMap = createPackageList(mMappingFile.getProject());
        loadPackageCombos();
    }

    /**
     * Create the JAXB package combo.
     * 
     * @param area the parent composite
     */
    private void initJaxbPackageCombo(final Composite area) {
        createLabel(area, Messages.structure_jaxb_package_label + ':');
        mJaxbPackageCombo = createCombo(area);
        mJaxbPackageCombo.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
                packageListChanged(mJaxbPackageCombo, mJaxbTypeList,
                        mStructure.getJaxbType());
            }

            public void widgetSelected(final SelectionEvent e) {
                packageListChanged(mJaxbPackageCombo, mJaxbTypeList,
                        mStructure.getJaxbType());
            }
        });
    }

    /**
     * Create the JAXB type combo.
     * 
     * @param area the parent composite
     */
    private void initJaxbTypeCombo(final Composite area) {
        createLabel(area, Messages.structure_jaxb_type_label + ':');
        mJaxbTypeList = createList(area);
        mJaxbTypeList.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
                mCobolRootDataItemNameText.setText("");
                dialogChanged();
            }

            public void widgetSelected(final SelectionEvent e) {
                mCobolRootDataItemNameText.setText("");
                dialogChanged();
            }
        });
    }

    /**
     * Fill the package lists in combo boxes. Strip the final .bind since we
     * want to show JAXB packages only.
     */
    private void loadPackageCombos() {
        for (String bindPkg : mPackageMap.keySet()) {
            String jaxbPkg = bindPkg.substring(0,
                    bindPkg.indexOf("." + BIND_FRAG, 0));
            mJaxbPackageCombo.add(jaxbPkg);
        }
        /* Make sure an item shows up in the text box */
        if (mPackageMap.size() > 0) {
            int i = 0;
            if (mStructure.getJaxbPackageName() != null) {
                i = mJaxbPackageCombo.indexOf(mStructure.getJaxbPackageName());
            }
            if (i > 0) {
                mJaxbPackageCombo.select(i);
            } else {
                mJaxbPackageCombo.select(0);
            }
            packageListChanged(mJaxbPackageCombo, mJaxbTypeList,
                    mStructure.getJaxbType());
        }
    }

    /** {@inheritDoc} */
    protected final void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.structure_mapping_dialog_title);
        ImageDescriptor image = AbstractUIPlugin.imageDescriptorFromPlugin(
                Activator.PLUGIN_ID, Messages.operations_mapping_icon);
        newShell.setImage(image.createImage());
    }

    /**
     * Populate the types list based on latest package selection.
     * 
     * @param pkgCombo the input or output package combo box
     * @param typeList the input or output type list box
     * @param type the current input or output type
     */
    private void packageListChanged(final Combo pkgCombo, final List typeList,
            final String type) {
        IPackageFragment pkgf = mPackageMap.get(pkgCombo.getText().trim() + "."
                + BIND_FRAG);
        if (pkgf != null) {
            java.util.List < String > typesList = classList(pkgf);
            typeList.removeAll();
            for (int i = 0; i < typesList.size(); i++) {
                /* Strip the binding suffix to show JAXB classes names */
                String bindType = typesList.get(i);
                int c = bindType.indexOf(BIND_SUFFIX, 0);
                if (c > 0) {
                    String jaxbType = bindType.substring(0, c);
                    typeList.add(jaxbType);
                }
            }
            if (typeList.getItemCount() > 0) {
                int i = 0;
                if (type != null) {
                    i = typeList.indexOf(type);
                }
                if (i > 0) {
                    typeList.select(i);
                } else {
                    typeList.select(0);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    /** {@inheritDoc} */
    protected final void okPressed() {
        mStructure.setCicsContainer(vlcNormalizedString(mCicsContainerText
                .getText()));
        mStructure.setJaxbPackageName(vlcNormalizedString(mJaxbPackageCombo
                .getText()));
        mStructure
                .setCobolRootDataItemName(vlcNormalizedString(mCobolRootDataItemNameText
                        .getText()));
        mStructure.setJaxbType(mJaxbTypeList.getSelection()[0]);
        setReturnCode(OK);
        close();
    }

    /**
     * The semantics of the velocity templates assumes null values when nothing
     * is provided. Therefore we should not be passing empty strings.
     * 
     * @param text the text to normalize
     * @return a normalized string
     * */
    private String vlcNormalizedString(final String text) {
        if (text == null) {
            return text;
        }
        if (text.trim().length() == 0) {
            return null;
        }
        return text.trim();
    }

    /**
     * Examines packages in a given project and adds segments which contain the
     * .bind suffix to the package list table. ".bind" suffix identify segments
     * that correspond to COXB binding packages.
     * 
     * @param project the java project to get packages from
     * @return map of java package names to fragments
     * @throws CoreException if the given project is not a Java project
     */
    private Map < String, IPackageFragment > createPackageList(
            final IProject project) throws CoreException {
        Map < String, IPackageFragment > packageMap = new HashMap < String, IPackageFragment >();
        IJavaProject jproject = JavaCore.create(project);
        IPackageFragmentRoot[] pkgRoots = jproject.getPackageFragmentRoots();
        for (int j = 0; j < pkgRoots.length; j++) {
            IPackageFragmentRoot pkgRoot = pkgRoots[j];
            if (pkgRoot.getKind() == IPackageFragmentRoot.K_SOURCE) {
                for (int k = 0; k < pkgRoot.getChildren().length; k++) {
                    IJavaElement el = pkgRoot.getChildren()[k];
                    if (el.getPath().lastSegment().compareTo(BIND_FRAG) == 0) {
                        packageMap.put(el.getElementName(),
                                (IPackageFragment) el);
                    }
                }
            }
        }
        return packageMap;
    }

    /**
     * Given a package fragment, this method gets all the Java compilation
     * units.
     * 
     * @param pkg the package fragment
     * @return a list of Java compilation units names within the package
     */
    private java.util.List < String > classList(final IPackageFragment pkg) {
        java.util.List < String > classes = new ArrayList < String >();
        try {
            ICompilationUnit[] compilationUnits = pkg.getCompilationUnits();
            for (int k = 0; k < compilationUnits.length; k++) {
                ICompilationUnit unit = compilationUnits[k];
                if (unit.isStructureKnown()) {
                    IType[] types = unit.getTypes();
                    for (int l = 0; l < types.length; l++) {
                        classes.add(types[l].getElementName());
                    }
                }
            }
        } catch (JavaModelException e) {
            return null;
        }
        Collections.sort(classes);
        return classes;
    }

    /**
     * Ensures that all fields are valid.
     */
    private void dialogChanged() {

        /* The project must contain at least one package with binding classes */
        if (mJaxbPackageCombo.getItemCount() == 0
                || mJaxbPackageCombo.getSelectionIndex() == -1) {
            updateStatus(false, NLS.bind(
                    Messages.no_coxb_classes_in_project_msg, mMappingFile
                            .getProject().getName()));
            return;
        }

        /* The selected package must contain at least one JAXB type */
        if (mJaxbTypeList.getItemCount() == 0) {
            updateStatus(false, Messages.no_coxb_classes_in_package_msg);
            return;
        }

        /* A JAXB type must be selected */
        if (mJaxbTypeList.getSelectionCount() < 1) {
            updateStatus(false, Messages.no_jaxb_type_selected_msg);
            return;
        }

        /*
         * Suggest a COBOL root element name if none is provided Otherwise,
         * check that content is valid COBOL.
         */
        if (mCobolRootDataItemNameText.getText().length() == 0) {
            mCobolRootDataItemNameText.setText(mCobolNameResolver
                    .getName(mJaxbTypeList.getSelection()[0]));
        } else {
            String validContent = mCobolNameResolver
                    .getName(mCobolRootDataItemNameText.getText());
            if (!validContent.equals(mCobolRootDataItemNameText.getText())) {
                updateStatus(false, Messages.invalid_structure_cobol_name_msg);
                return;
            }
        }
        /* TODO check for CICS container maximum size (16) */

        updateStatus(true, null);
    }

    /**
     * Shows error messages if any and enables/disables the OK button.
     * 
     * @param valid if dialog data is valid
     * @param message the error message to display
     */
    private void updateStatus(final boolean valid, final String message) {
        if (!valid) {
            errorDialog(Messages.structure_mapping_error_dialog_title, message);
        }
        if (getButton(IDialogConstants.OK_ID) != null) {
            if (valid) {
                getButton(IDialogConstants.OK_ID).setEnabled(true);
            } else {
                getButton(IDialogConstants.OK_ID).setEnabled(false);
            }
        }

    }

    /**
     * @return structure being edited
     */
    public CixsStructure getStructure() {
        return mStructure;
    }

    /**
     * @param structure structure being edited
     */
    public void setStructure(final CixsStructure structure) {
        this.mStructure = structure;
    }

}
