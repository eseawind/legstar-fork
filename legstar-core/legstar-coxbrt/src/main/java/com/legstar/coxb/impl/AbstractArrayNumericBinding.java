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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.legstar.coxb.CobolElement;
import com.legstar.coxb.ICobolComplexBinding;
import com.legstar.coxb.common.CArrayBinding;
import com.legstar.coxb.host.HostException;

/**
 * Generic class for numeric arrays bindings.
 * 
 */
public abstract class AbstractArrayNumericBinding extends CArrayBinding {

    /** The current list for this array. */
    private List < BigDecimal > mList = null;

    /**
     * Constructor for a cobol element to java binding.
     * 
     * @param bindingName the identifier for this binding
     * @param jaxbName the name of the bound java property
     * @param jaxbType the type of the bound java property
     * @param cobolAnnotations the cobol annotations for this element
     * @param parentBinding a reference to the parent binding if any
     */
    public AbstractArrayNumericBinding(final String bindingName,
            final String jaxbName, final Class < ? > jaxbType,
            final CobolElement cobolAnnotations,
            final ICobolComplexBinding parentBinding) {
        super(bindingName, jaxbName, jaxbType, cobolAnnotations, parentBinding);
    }

    /**
     * @return the internal List as Bytes
     */
    public List < Byte > getByteList() {
        List < Byte > list = new ArrayList < Byte >();
        for (BigDecimal value : mList) {
            list.add(value.byteValue());
        }
        return list;
    }

    /**
     * @param list the internal List of Bytes to set
     */
    public void setByteList(final List < Byte > list) {
        mList = new ArrayList < BigDecimal >();
        for (Byte value : list) {
            mList.add(new BigDecimal(value));
        }
    }

    /**
     * @return the internal List as Shorts
     */
    public List < Short > getShortList() {
        List < Short > list = new ArrayList < Short >();
        for (BigDecimal value : mList) {
            list.add(value.shortValue());
        }
        return list;
    }

    /**
     * @param list the internal List of Shorts to set
     */
    public void setShortList(final List < Short > list) {
        mList = new ArrayList < BigDecimal >();
        for (Short value : list) {
            mList.add(new BigDecimal(value));
        }
    }

    /**
     * @return the internal List as Integers
     */
    public List < Integer > getIntegerList() {
        List < Integer > list = new ArrayList < Integer >();
        for (BigDecimal value : mList) {
            list.add(value.intValue());
        }
        return list;
    }

    /**
     * @param list the internal List of Integers to set
     */
    public void setIntegerList(final List < Integer > list) {
        mList = new ArrayList < BigDecimal >();
        for (Integer value : list) {
            mList.add(new BigDecimal(value));
        }
    }

    /**
     * @return the internal List as Longs
     */
    public List < Long > getLongList() {
        List < Long > list = new ArrayList < Long >();
        for (BigDecimal value : mList) {
            list.add(value.longValue());
        }
        return list;
    }

    /**
     * @param list the internal List of Longs to set
     */
    public void setLongList(final List < Long > list) {
        mList = new ArrayList < BigDecimal >();
        for (Long value : list) {
            mList.add(new BigDecimal(value));
        }
    }

    /**
     * @return the internal List of BigDecimals
     */
    public List < BigDecimal > getBigDecimalList() {
        return mList;
    }

    /**
     * @param list the internal List of BigDecimals to set
     */
    public void setBigDecimalList(final List < BigDecimal > list) {
        mList = list;
    }

    /**
     * @return the internal List as BigIntegers
     */
    public List < BigInteger > getBigIntegerList() {
        List < BigInteger > list = new ArrayList < BigInteger >();
        for (BigDecimal value : mList) {
            list.add(value.toBigInteger());
        }
        return list;
    }

    /**
     * @param list the internal List of BigIntegers to set
     */
    public void setBigIntegerList(final List < BigInteger > list) {
        mList = new ArrayList < BigDecimal >();
        for (BigInteger value : list) {
            mList.add(new BigDecimal(value));
        }
    }

    /**
     * @return the internal List as Boolean
     */
    public List < Boolean > getBooleanList() {
        List < Boolean > list = new ArrayList < Boolean >();
        for (BigDecimal value : mList) {
            if (value.intValue() == 0) {
                list.add(Boolean.FALSE);
            } else {
                list.add(Boolean.TRUE);
            }
        }
        return list;
    }

    /**
     * @param list the internal List of Boolean to set
     */
    public void setBooleanList(final List < Boolean > list) {
        mList = new ArrayList < BigDecimal >();
        for (Boolean value : list) {
            mList.add(new BigDecimal((value) ? "1" : "0"));
        }
    }

    /** {@inheritDoc} */
    public Object getObjectValue(final Class < ? > type) throws HostException {
        if (type.equals(BigDecimal.class)) {
            return mList;
        } else if (type.equals(BigInteger.class)) {
            return getBigIntegerList();
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return getLongList();
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return getIntegerList();
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return getShortList();
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return getByteList();
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return getBooleanList();
        } else {
            throw new HostException("Attempt to get binding "
                    + getBindingName() + " as an incompatible type " + type);
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
                mList = new ArrayList < BigDecimal >();
                return;
            }
            /*
             * We assume all items will have the same type as the first one. The
             * unchecked cast might break at runtime.
             */
            Object item = ((List < ? >) value).get(0);
            if (item instanceof BigDecimal) {
                mList = (List < BigDecimal >) value;
                return;
            } else if (item instanceof BigInteger) {
                setBigIntegerList((List < BigInteger >) value);
                return;
            } else if (item instanceof Long) {
                setLongList((List < Long >) value);
                return;
            } else if (item instanceof Integer) {
                setIntegerList((List < Integer >) value);
                return;
            } else if (item instanceof Short) {
                setShortList((List < Short >) value);
                return;
            } else if (item instanceof Byte) {
                setByteList((List < Byte >) value);
                return;
            } else if (item instanceof Boolean) {
                setBooleanList((List < Boolean >) value);
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
