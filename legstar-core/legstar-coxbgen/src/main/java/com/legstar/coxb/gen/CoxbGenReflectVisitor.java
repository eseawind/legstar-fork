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
package com.legstar.coxb.gen;

import java.io.File;

import com.legstar.coxb.CobolElementVisitor;
import com.legstar.coxb.ICobolArrayBinaryBinding;
import com.legstar.coxb.ICobolArrayComplexBinding;
import com.legstar.coxb.ICobolArrayDbcsBinding;
import com.legstar.coxb.ICobolArrayDoubleBinding;
import com.legstar.coxb.ICobolArrayFloatBinding;
import com.legstar.coxb.ICobolArrayNationalBinding;
import com.legstar.coxb.ICobolArrayOctetStreamBinding;
import com.legstar.coxb.ICobolArrayPackedDecimalBinding;
import com.legstar.coxb.ICobolArrayStringBinding;
import com.legstar.coxb.ICobolArrayZonedDecimalBinding;
import com.legstar.coxb.ICobolBinaryBinding;
import com.legstar.coxb.ICobolBinding;
import com.legstar.coxb.ICobolChoiceBinding;
import com.legstar.coxb.ICobolComplexBinding;
import com.legstar.coxb.ICobolDbcsBinding;
import com.legstar.coxb.ICobolDoubleBinding;
import com.legstar.coxb.ICobolFloatBinding;
import com.legstar.coxb.ICobolNationalBinding;
import com.legstar.coxb.ICobolOctetStreamBinding;
import com.legstar.coxb.ICobolPackedDecimalBinding;
import com.legstar.coxb.ICobolStringBinding;
import com.legstar.coxb.ICobolZonedDecimalBinding;
import com.legstar.coxb.host.HostException;

/**
 * This class implements the visitor pattern in order to reflect on a JAXB
 * object tree instance. Each complex element, choice element and complex
 * array generates a separate java class which code is generated using
 * appropriate velocity templates.
 * 
 * @author Fady Moussallam
 * 
 */
public class CoxbGenReflectVisitor extends CobolElementVisitor {

    /** Actual code generator. */
    private CoxbGenWriter _writer;

    /**
     * @return the actual code generator
     */
    public CoxbGenWriter getWriter() {
        return _writer;
    }

    /**
     * @param writer the actual code generator to set
     */
    public void setWriter(final CoxbGenWriter writer) {
        _writer = writer;
    }

    /**
     * Constructor.
     * 
     * @param coxbGenModel set of parameters
     * @param outputFolder where files need to be generated
     * @throws HostException if directory provided is not accessible
     */
    public CoxbGenReflectVisitor(
            final CoxbGenModel coxbGenModel, final File outputFolder)
            throws HostException {
        try {
            _writer = new CoxbGenWriter(coxbGenModel, outputFolder);
        } catch (CoxbGenException e) {
            throw new HostException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolComplexBinding ce)
            throws HostException {

        /*
         * Create an ordered list of properties in this complex element.
         * Since we are unmarshaling, bound objects do not pre-exist so
         * we need to create them.
         */
        ce.createValueObject();
        java.util.List < ICobolBinding > children =
                ce.getChildrenList();

        /*
         * Iteratively propagate the accept on complex element children.
         * The order in which children are processed is important.
         */
        for (ICobolBinding child : children) {
            child.accept(this);
        }

        /*
         * Now that all children have been processed, its time to generate
         * this element binding
         */
        try {
            _writer.write(ce);
        } catch (CoxbGenException e) {
            throw new HostException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolChoiceBinding ce)
            throws HostException {

        /* We want to reflect on all alternatives */
        for (ICobolBinding alt : ce.getAlternativesList()) {
            try {
                alt.accept(this);
            } catch (HostException he) {
                continue;
            }
        }
        /*
         * Now that all alternatives have been processed, its time to generate
         * this element binding
         */
        try {
            _writer.write(ce);
        } catch (CoxbGenException e) {
            throw new HostException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayComplexBinding ce)
            throws HostException {
        /* We reflect on only one item of the array */
        ce.createValueObject();
        ICobolBinding itemDesc = ce.getComplexItemBinding();
        itemDesc.accept(this);

        /*
         * Now that an item has been processed, its time to generate
         * this element binding
         */
        try {
            _writer.write(ce);
        } catch (CoxbGenException e) {
            throw new HostException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolStringBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayStringBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolNationalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayNationalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolDbcsBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayDbcsBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolZonedDecimalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayZonedDecimalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolPackedDecimalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayPackedDecimalBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolBinaryBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayBinaryBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolFloatBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayFloatBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolDoubleBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayDoubleBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolOctetStreamBinding ce)
            throws HostException {
    }

    /** {@inheritDoc} */
    @Override
    public void visit(final ICobolArrayOctetStreamBinding ce)
            throws HostException {
    }

}
