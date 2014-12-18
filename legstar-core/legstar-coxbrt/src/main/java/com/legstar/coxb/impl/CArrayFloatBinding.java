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
import com.legstar.coxb.ICobolArrayFloatBinding;
import com.legstar.coxb.ICobolComplexBinding;
import com.legstar.coxb.CobolElementVisitor;
import com.legstar.coxb.common.CArrayBinding;
import com.legstar.coxb.host.HostException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the behavior of an array of comp-1 cobol elements
 * bound to a JAXB Float property.
 *
 * @author Fady Moussallam
 * 
 */
public class CArrayFloatBinding extends CArrayBinding implements ICobolArrayFloatBinding {

    /** The current list for this array. */
    private List < Float > mList = null;

    /**
     * Constructor for a cobol element to java binding.
     * 
     * @param bindingName the identifier for this binding
     * @param jaxbName the name of the bound java property
     * @param jaxbType the type of the bound java property
     * @param cobolAnnotations the cobol annotations for this element
     * @param parentBinding a reference to the parent binding if any
     */
    public CArrayFloatBinding(
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
    public int calcItemByteLength() {
        return CFloatBinding.calcFloatByteLength();
    }

    /**
     * @return the List of items
     */
    public List < Float > getFloatList() {
        return mList;
    }

    /**
     * @param list the items List to set
     */
    public void setFloatList(
            final List < Float > list) {
        mList = list;
    }

    /**
     * @return the internal List as BigDecimals
     */
    public List < BigDecimal > getBigDecimalList() {
        List < BigDecimal > list = new ArrayList < BigDecimal >();
        for (Float value : mList) {
            list.add(new BigDecimal(value));
        }
        return list;
    }

    /**
     * @param list the internal List of BigDecimals to set
     */
    public void setBigDecimalList(
            final List < BigDecimal > list) {
        mList = new ArrayList < Float >();
        for (BigDecimal value : list) {
            mList.add(value.floatValue());
        }
    }

    /** {@inheritDoc} */
    public Object getObjectValue(
            final Class < ? > type) throws HostException {
        if (type.equals(Float.class)) {
            return mList;
        } else if (type.equals(BigDecimal.class)) {
            return getBigDecimalList();
        } else {
            throw new HostException("Attempt to get binding " + getBindingName()
                    + " as an incompatible type " + type);
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public void setObjectValue(final Object value) throws HostException {
        if (value == null) {
            mList = null;
            return;
        }
        if (value instanceof List) {
            if (((List < ? >) value).size() == 0) {
                mList = new ArrayList < Float >();
                return;
            }
            /* We assume all items will have the same type as the first one.
             * The unchecked cast might break at runtime. */
            Object item = ((List < ? >) value).get(0);
            if (item instanceof Float) {
                mList = (List < Float >) value;
                return;
            } else if (item instanceof BigDecimal) {
                setBigDecimalList((List < BigDecimal >) value);
                return;
            }
        }
        throw new HostException("Attempt to set binding " + getBindingName()
                + " from an incompatible value " + value);
    }

    /** {@inheritDoc} */
    public boolean isSet() {
        return (mList != null);
    }

}
