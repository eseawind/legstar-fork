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

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.legstar.codegen.CodeGenUtil;
import com.legstar.codegen.models.AbstractPropertiesModel;

/**
 * This class describes a mapping between list of operations and
 * their CICS programs counterparts.
 * This is a generic mapping that is usable for many generation
 * targets.
 * 
 * @author Fady Moussallam
 * 
 */
public class CixsMappingModel extends AbstractPropertiesModel {

    /** Mapping name. */
    private String _name;

    /** List of operations provided by this mapping. */
    private List < CixsOperation > _cixsOperations =
            new ArrayList < CixsOperation >();

    /**
     * For compatibility with previous versions, we support this
     * tag CIXS Jaxws service definition.
     */
    public static final String CIXS_COMPAT_SERVICE_XML_E = "cixsJaxwsService";

    /** XML element representing a CIXS mapping definition. */
    public static final String CIXS_MAPPING_XML_E = "cixsMapping";

    /** XML attribute representing a CIXS mapping name. */
    public static final String CIXS_MAPPING_NAME_XML_A = "name";

    /**
     * Construct an empty model.
     */
    public CixsMappingModel() {
        super();
    }

    /**
     * Construct from a properties file.
     * 
     * @param props the property file
     */
    public CixsMappingModel(final Properties props) {
        super(props);
    }

    /**
     * @return the mapping name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param name the mapping name to set
     */
    public void setName(final String name) {
        _name = javaNormalize(name);
    }

    /**
     * TODO Move this to common utility class.
     * The service name is used in various places as a Java identifier. This
     * creates a valid Java identifier from a proposed string.
     * 
     * @param str the input string
     * @return a a valid java identifier
     */
    public static String javaNormalize(final String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (i == 0) {
                if (Character.isJavaIdentifierStart(c)) {
                    sb.append(c);
                } else {
                    sb.append("C");
                }
            } else {
                if (Character.isJavaIdentifierPart(c)) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * @return the service list of operations
     */
    public List < CixsOperation > getCixsOperations() {
        return _cixsOperations;
    }

    /**
     * @param cixsOperations the service list of operations to set
     */
    public void setCixsOperations(
            final List < CixsOperation > cixsOperations) {
        _cixsOperations = cixsOperations;
    }

    /**
     * Operations should actually a set of uniquely named operations.
     * Ant seems to call this method with uninitialized objects though,
     * therefore there is no point in checking for name conflicts here.
     * 
     * @param operation the operation to add
     */
    public void addCixsOperation(
            final CixsOperation operation) {
        _cixsOperations.add(operation);
    }

    /**
     * Create an XML usable as input for and ant task.
     * 
     * @return the XML
     */
    public String serialize() {
        StringBuffer result = new StringBuffer();
        result.append("<" + CIXS_MAPPING_XML_E + " "
                + CIXS_MAPPING_NAME_XML_A + "="
                + '\"' + _name + '\"');
        result.append('>' + CodeGenUtil.CRLF);
        for (CixsOperation op : getCixsOperations()) {
            result.append(op.serialize());
            result.append(CodeGenUtil.CRLF);
        }
        result.append("</" + CIXS_MAPPING_XML_E + ">");
        return result.toString();
    }

    /**
     * Loads the CIXS Service from a serialized XML.
     * 
     * @param serviceFile the serialized file
     * @throws CixsModelException if load fails
     */
    public void load(final File serviceFile) throws CixsModelException {
        DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilderFactory.setNamespaceAware(false);
            docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(serviceFile);
            load(doc);
        } catch (ParserConfigurationException e) {
            throw (new CixsModelException(e));
        } catch (SAXException e) {
            throw (new CixsModelException(e));
        } catch (IOException e) {
            throw (new CixsModelException(e));
        }
    }

    /**
     * Loads the CIXS Service from a serialized XML in a string.
     * 
     * @param serviceDesc the service description
     * @throws CixsModelException if load fails
     */
    public void load(final String serviceDesc) throws CixsModelException {
        DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilderFactory.setNamespaceAware(false);
            docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(
                    new InputSource(new StringReader(serviceDesc)));
            load(doc);
        } catch (ParserConfigurationException e) {
            throw (new CixsModelException(e));
        } catch (SAXException e) {
            throw (new CixsModelException(e));
        } catch (IOException e) {
            throw (new CixsModelException(e));
        }
    }

    /**
     * Loads the CIXS Service from an XML document.
     * 
     * @param doc an XML document
     * @throws CixsModelException if load fails
     */
    public void load(final Document doc) throws CixsModelException {
        NodeList listOfElements = doc.getElementsByTagName(
                CIXS_MAPPING_XML_E);
        if (listOfElements == null || listOfElements.getLength() == 0) {
            /* This might be a previous version mapping file */
            listOfElements = doc.getElementsByTagName(
                    CIXS_COMPAT_SERVICE_XML_E);
            if (listOfElements == null || listOfElements.getLength() == 0) {
                throw (new CixsModelException(
                        "Empty or invalid service descriptor file"));
            }
        }
        try {
            Element serviceElement = (Element) listOfElements.item(0);
            _name = serviceElement.getAttribute(CIXS_MAPPING_NAME_XML_A);
            if (_name == null || _name.length() == 0) {
                throw new CixsModelException("Service must have a name");
            }
            getCixsOperations().clear();
            listOfElements = serviceElement.getElementsByTagName(
                    CixsOperation.CIXS_OPERATION_XML_E);
            for (int i = 0; i < listOfElements.getLength(); i++) {
                CixsOperation operation = new CixsOperation();
                operation.load(listOfElements.item(i));
                addCixsOperation(operation);
            }
        } catch (CixsModelException e) {
            throw new CixsModelException(e);
        }
    }

    /**
     * @see Object#hashCode()
     *      {@inheritDoc}
     */
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Indicates whether some other service is "equal to" this one.
     * 
     * @param obj Object to be compared.
     * @return true if this object is the same as the obj argument; false
     *         otherwise..
     */
    public boolean equals(final Object obj) {
        return (obj != null) && (obj.getClass() == CixsMappingModel.class)
                && ((CixsMappingModel) obj).getName().equals(getName());
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * 
     * @param o Object to be compared.
     * @return A negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    public int compareTo(final Object o) {
        if (o.getClass() != CixsMappingModel.class) {
            throw new ClassCastException(o.getClass().getName());
        } else {
            return ((CixsMappingModel) o).getName().compareTo(getName());
        }
    }

}
