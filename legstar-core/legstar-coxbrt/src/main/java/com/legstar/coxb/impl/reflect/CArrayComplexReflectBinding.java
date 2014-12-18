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
package com.legstar.coxb.impl.reflect;

import java.util.ArrayList;
import java.util.List;

import com.legstar.coxb.CobolComplexType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.common.CArrayComplexBinding;
import com.legstar.coxb.host.HostException;
import com.legstar.coxb.ICobolComplexBinding;

/**
 * Cobol/JAXB implementation of an array of complex (record) elements.
 *
 * @author Fady Moussallam
 * 
 */
public class CArrayComplexReflectBinding extends CArrayComplexBinding {

    /** This is a reference to a JAXB object factory. */
    private Object mJaxbObjectFactory;

    /** Java object to which this cobol complex array element is bound. */
    private List < Object > mJaxbObject;

    /**
     * Creates a binding between a Cobol array of complex elements and a
     * java List.
     * 
     * @param bindingName the identifier for this binding
     * @param jaxbName the name of the bound java property
     * @param jaxbType the type of the bound java property
     * @param cobolAnnotations the cobol annotations for this element
     * @param parentBinding a reference to the parent binding if any
     * @param complexItemBinding a binding element for array items
     * @param objectFactory the JAXB object factory
     */
    public CArrayComplexReflectBinding(
            final String bindingName,
            final String jaxbName,
            final Class < ? > jaxbType,
            final CobolElement cobolAnnotations,
            final ICobolComplexBinding parentBinding,
            final ICobolComplexBinding complexItemBinding,
            final Object objectFactory) {

        super(bindingName, jaxbName, jaxbType, cobolAnnotations, parentBinding,
                complexItemBinding);
        mJaxbObjectFactory = objectFactory;
        /* Assume we are bound to a JAXB object */
        setValueObjectClassName(jaxbType.getName());
        setValueObjectsFactoryClassName(objectFactory.getClass().getName());
        /* Jaxb class might hold an annotation which gives more details
         * on how to bind*/
        CobolComplexType cobolComplexType =
            (CobolComplexType) jaxbType.getAnnotation(CobolComplexType.class);
        if (cobolComplexType != null
                && cobolComplexType.javaClassName() != null
                && cobolComplexType.javaClassName().length() > 0) {
            setValueObjectClassName(cobolComplexType.javaClassName());
            /* TODO allow more options, such as factory name, to be 
             * passed as annotations */
            setValueObjectsFactoryClassName(null);
        }
    }

    /** {@inheritDoc}
     * @deprecated */
    public void createJaxbObject() throws HostException {
        createValueObject();
    }

    /** {@inheritDoc} */
    public void createValueObject() throws HostException {
        mJaxbObject = new ArrayList < Object >();
    }

    /** {@inheritDoc} */
    public void setItemValue(
            final int index) throws HostException {
        /* Make sure there is an associated JAXB object*/
        if (mJaxbObject == null) {
            createJaxbObject();
        }
        /* The Jaxb list might have less items than expected by the binding.
         * In this case, we fill the binding with empty items. */
        if (index < mJaxbObject.size()) {
            getComplexItemBinding().setObjectValue(mJaxbObject.get(index));
        } else {
            getComplexItemBinding().setObjectValue(null);
        }
    }

    /** {@inheritDoc}
     * @deprecated */
    public void addJaxbPropertyValue(
            final int index) throws HostException {
        addPropertyValue(index);
    }

    /** {@inheritDoc} */
    public void addPropertyValue(
            final int index) throws HostException {
        /* Make sure there is an associated JAXB object*/
        if (mJaxbObject == null) {
            throw new HostException(
                    "Binded object not initialized for " + getBindingName());
        }
        mJaxbObject.add(getComplexItemBinding().getObjectValue(getJaxbType()));
    }

    /**
     * @return Returns the JAXB Object Factory.
     */
    public Object getObjectFactory() {
        return mJaxbObjectFactory;
    }

    /**
     * @return the List of items
     */
    public List < ? > getObjectList() {
        return mJaxbObject;
    }

    /**
     * @param list the items List to set
     */
    @SuppressWarnings("unchecked")
    public void setObjectList(final List < ? > list) {
        mJaxbObject = ( List < Object >) list;
    }

    /** {@inheritDoc} */
    public Object getObjectValue(
            final Class < ? > type) throws HostException {
        if (type.equals(getJaxbType())) {
            return mJaxbObject;
        } else {
            throw new HostException("Attempt to get binding " + getBindingName()
                    + " as an incompatible type " + type);
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public void setObjectValue(final Object value) throws HostException {
        if (value == null) {
            mJaxbObject = null;
            return;
        }
        if (value instanceof List) {
            if (((List < ? >) value).size() == 0) {
                mJaxbObject = new ArrayList < Object >();
                return;
            }
            /* We assume all items will have the same type as the first one.
             * The unchecked cast might break at runtime. */
            Object item = ((List < ? >) value).get(0);
            if (item.getClass().equals(getJaxbType())) {
                mJaxbObject = (List < Object >) value;
                return;
            }
        }
        throw new HostException("Attempt to set binding " + getBindingName()
                + " from an incompatible value " + value);
    }

    /** {@inheritDoc} */
    public boolean isSet() {
        return (mJaxbObject != null);
    }
}
