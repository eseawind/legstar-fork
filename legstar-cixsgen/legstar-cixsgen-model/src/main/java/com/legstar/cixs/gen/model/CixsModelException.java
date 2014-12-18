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
package com.legstar.cixs.gen.model;

/**
 * Exception related to an inconsistent or erroneous model.
 */
public class CixsModelException extends Exception {

    /** Unique serial ID. */
    private static final long serialVersionUID = -8210427970123669600L;

    /**
     * Build Exception from message.
     * @param message exception description
     */
    public CixsModelException(final String message) {
        super(message);
    }

  /**
   * Build Exception from inner exception.
   * @param e the inner exception
   */
    public CixsModelException(final Exception e) {
        super(e);
    }
}

