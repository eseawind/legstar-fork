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

/**
 * Exception raised if invoke execution fails.
 */
public class HostInvokerException extends Exception {

    /** Serial ID. */
    private static final long serialVersionUID = -4069744829224499341L;

    /** 
     * Constructor from an error message. 
     * @param message the text message 
     * */
    public HostInvokerException(final String message) {
        super(message);
    }

    /** 
     * Constructor from an inner exception. 
     * @param e the inner exception 
     * */
    public HostInvokerException(final Exception e) {
        super(e);
    }
}
