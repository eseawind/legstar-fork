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
package com.legstar.coxb;

import java.math.BigDecimal;

import com.legstar.coxb.host.HostException;

/**
 * This interface groups methods that are common to float elements that are
 * not arrays.
 * 
 * @author Fady Moussallam
 * 
 */
public interface ICobolFloatBinding extends ICobolBinding {

    /**
     * Returns this element value.
     * @return Element value
     * @throws HostException if value cannot be retrieved
     */
    Float getFloatValue() throws HostException;

    /**
     * Sets the element value.
     * @param value Value to set
     * @throws HostException if value cannot be set
     */
    void setFloatValue(Float value) throws HostException;

    /**
     * Returns this element value as a BigDecimal.
     * @return Element value
     * @throws HostException if value cannot be retrieved
     */
    BigDecimal getBigDecimalValue() throws HostException;

    /**
     * Sets the element value.
     * @param value Value to set as a BigDecimal
     * @throws HostException if value cannot be set
     */
    void setBigDecimalValue(BigDecimal value) throws HostException;
}
