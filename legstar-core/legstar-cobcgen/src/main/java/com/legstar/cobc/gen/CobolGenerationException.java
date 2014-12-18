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
package com.legstar.cobc.gen;

/**
 * Exception while trying to generate COBOL code.
 */
public class CobolGenerationException extends Exception {

    /** Unique serial ID. */
    private static final long serialVersionUID = -2435961612804076829L;

    /**
     * Build Exception from message.
     * @param message exception description
     */
    public CobolGenerationException(final String message) {
        super(message);
    }

    /**
     * Build Exception from inner exception.
     * @param e the inner exception
     */
    public CobolGenerationException(final Exception e) {
        super(e);
    }
}

