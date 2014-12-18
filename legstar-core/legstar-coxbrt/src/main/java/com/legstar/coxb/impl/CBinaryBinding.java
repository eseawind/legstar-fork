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
package com.legstar.coxb.impl;

import com.legstar.coxb.CobolElement;
import com.legstar.coxb.ICobolBinaryBinding;
import com.legstar.coxb.ICobolComplexBinding;
import com.legstar.coxb.CobolElementVisitor;
import com.legstar.coxb.host.HostException;

/**
 * This class implements the behavior of a numeric binary cobol element bound to
 * a JAXB BigDecimal property.
 *
 * @author Fady Moussallam
 * 
 */
public class CBinaryBinding
extends AbstractNumericBinding
implements ICobolBinaryBinding {

    /**
     * Constructor for a cobol element to java binding.
     * 
     * @param bindingName the identifier for this binding
     * @param jaxbName the name of the bound java property
     * @param jaxbType the type of the bound java property
     * @param cobolAnnotations the cobol annotations for this element
     * @param parentBinding a reference to the parent binding
     */
    public CBinaryBinding(
            final String bindingName,
            final String jaxbName,
            final Class < ? > jaxbType,
            final CobolElement cobolAnnotations,
            final ICobolComplexBinding parentBinding) {
        super(bindingName, jaxbName, jaxbType, cobolAnnotations, parentBinding);
    }

    /** {@inheritDoc} */
    public void accept(final CobolElementVisitor cev)
    throws HostException {
        cev.visit(this);
    }
    /** {@inheritDoc} */
    public int calcByteLength() {
        return calcBinaryByteLength(getTotalDigits());
    }

    /**
     * Calculates the host byte length for a COMP.
     * PIC S9(1) to S9(4) occupies 2 bytes (short).
     * PIC S9(5) to S9(9) occupies 4 bytes (integer).
     * PIC S9(10) to S9(18) occupies 8 bytes (long).
     * @param totalDigits the number of digits (including fraction digits)
     * @return the host byte length for a COMP
     */
    public static int calcBinaryByteLength(final int totalDigits) {
        if (totalDigits < 5) {
            return 2;
        } else if (totalDigits < 10) {
            return 4;
        } else {
            return 8;
        }
    }
}
