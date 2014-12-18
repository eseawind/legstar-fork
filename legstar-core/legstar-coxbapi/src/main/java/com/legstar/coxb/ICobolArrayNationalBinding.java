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

import java.util.List;

import com.legstar.coxb.host.HostException;

/**
 * This interface groups methods that are common to all cobol array national
 * elements (PIC N OCCURS USAGE NATIONAL).
 *
 * @author Fady Moussallam
 * 
 */
public interface ICobolArrayNationalBinding extends ICobolArrayBinding {


    /**
     * @return Returns an ArrayList of Strings 
     * @throws HostException if list cannot be returned
     */
    List < String > getStringList() throws HostException;

    /**
     * Set a string array element.
     * @param iArray set array to set
     * @throws HostException if list cannot be set
     */
    void setStringList(List < String > iArray) throws HostException;

    /**
     * Corresponding cobol field must be right justified.
     * @return True if field is right justified
     */
    boolean isJustifiedRight();

}
