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
package com.legstar.test.coxb;

import junit.framework.TestCase;

import com.legstar.test.coxb.lsfileae.ComPersonal;
import com.legstar.test.coxb.lsfileae.Dfhcommarea;
import com.legstar.test.coxb.lsfileae.ObjectFactory;

/**
 * Provides data samples for testing throughout LegStar.
 */
public class LsfileaeCases extends TestCase {

    /** Utility class. */
    private LsfileaeCases() {

    }

    /**
     * @return an instance of a valued java object.
     */
    public static Dfhcommarea getJavaObject() {
        ObjectFactory of = new ObjectFactory();
        Dfhcommarea dfhcommarea = of.createDfhcommarea();
        dfhcommarea.setComNumber(100L);
        ComPersonal comPersonal = new ComPersonal();
        comPersonal.setComName("TOTO");
        comPersonal.setComAddress("LABAS STREET");
        comPersonal.setComPhone("88993314");
        dfhcommarea.setComPersonal(comPersonal);
        dfhcommarea.setComDate("100458");
        dfhcommarea.setComAmount("00100.35");
        dfhcommarea.setComComment("A VOIR");
        return dfhcommarea;
    }

    /**
     * @return an XML serialization of the object
     */
    public static String getXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<Dfhcommarea xmlns=\"http://legstar.com/test/coxb/lsfileae\">"
                + "<ComNumber>100</ComNumber>"
                + "<ComPersonal>"
                + "<ComName>TOTO</ComName>"
                + "<ComAddress>LABAS STREET</ComAddress>"
                + "<ComPhone>88993314</ComPhone>"
                + "</ComPersonal>"
                + "<ComDate>100458</ComDate>"
                + "<ComAmount>00100.35</ComAmount>"
                + "<ComComment>A VOIR</ComComment>"
                + "</Dfhcommarea>";
    }

    /**
     * @return a JSON serialization of the object
     */
    public static String getJson() {
        return "{\"ComNumber\":100,"
                + "\"ComPersonal\":"
                + "{\"ComName\":\"TOTO\","
                + "\"ComAddress\":\"LABAS STREET\","
                + "\"ComPhone\":\"88993314\"},"
                + "\"ComDate\":\"100458\","
                + "\"ComAmount\":\"00100.35\","
                + "\"ComComment\":\"A VOIR\"}";
    }

    /**
     * @return an XML serialization of an inner object
     */
    public static String getComPersonalXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<ComPersonal xmlns=\"http://legstar.com/test/coxb/lsfileae\">"
                + "<ComName>TOTO</ComName>"
                + "<ComAddress>LABAS STREET</ComAddress>"
                + "<ComPhone>88993314</ComPhone>"
                + "</ComPersonal>";
    }

    /**
     * Check that data object contains the expected values.
     * 
     * @param dfhcommarea the java object to check
     */
    public static void checkJavaObject(final Dfhcommarea dfhcommarea) {
        assertEquals(100, dfhcommarea.getComNumber());
        assertEquals("TOTO", dfhcommarea.getComPersonal().getComName().trim());
        assertEquals("LABAS STREET", dfhcommarea.getComPersonal()
                .getComAddress().trim());
        assertEquals("88993314", dfhcommarea.getComPersonal().getComPhone()
                .trim());
        assertEquals("100458", dfhcommarea.getComDate().trim());
        assertEquals("00100.35", dfhcommarea.getComAmount().trim());
        assertEquals("A VOIR", dfhcommarea.getComComment().trim());
    }

    /**
     * @return a hexadecimal representation of host data.
     */
    public static String getHostBytesHex() {

        return "f0f0f0f1f0f0"
                + "e3d6e3d640404040404040404040404040404040"
                + "d3c1c2c1e240e2e3d9c5c5e34040404040404040"
                + "f8f8f9f9f3f3f1f4"
                + "f1f0f0f4f5f84040"
                + "f0f0f1f0f04bf3f5"
                + "c140e5d6c9d9404040";
    }

    /**
     * @return an instance of a valued java object.
     */
    public static Dfhcommarea getJavaObjectRequest100() {
        ObjectFactory of = new ObjectFactory();
        Dfhcommarea dfhcommarea = of.createDfhcommarea();
        dfhcommarea.setComNumber(100L);
        return dfhcommarea;
    }

    /**
     * @return a hexadecimal representation of host data.
     */
    public static String getHostBytesHexRequest100() {

        return
        /* 0 0 0 1 0 0 */
        "f0f0f0f1f0f0";
    }

    /**
     * @return a hexadecimal representation of host data.
     */
    public static String getHostBytesHexReply100() {

        return
        /* 0 0 0 1 0 0 */
        "f0f0f0f1f0f0"
                /* S . D . B O R M A N */
                + "e24b40c44b40c2d6d9d4c1d54040404040404040"
                /* L A B A S S T R E E T */
                + "e2e4d9d9c5e86b40c5d5c7d3c1d5c44040404040"
                /* 3 2 1 5 6 7 7 8 */
                + "f3f2f1f5f6f7f7f8"
                /* 2 6 1 1 8 1 */
                + "f2f640f1f140f8f1"
                /* $ 0 1 0 0 . 1 1 */
                + "5bf0f1f0f04bf1f1"
                /*  * * * * * * * * * */
                + "5c5c5c5c5c5c5c5c5c";
    }

    /**
     * Check the values returned from LSFILAE after they were transformed to
     * Java.
     * 
     * @param dfhcommarea the java data object
     */
    public static void checkJavaObjectReply100(final Dfhcommarea dfhcommarea) {
        assertEquals(100, dfhcommarea.getComNumber());
        assertEquals("$0100.11", dfhcommarea.getComAmount());
        assertEquals("*********", dfhcommarea.getComComment());
        assertEquals("26 11 81", dfhcommarea.getComDate());
        assertEquals("SURREY, ENGLAND", dfhcommarea.getComPersonal()
                .getComAddress());
        assertEquals("S. D. BORMAN", dfhcommarea.getComPersonal().getComName());
        assertEquals("32156778", dfhcommarea.getComPersonal().getComPhone());
    }

    /**
     * @return a JAXB object factory for this type of object
     */
    public static Object getFactory() {
        return new ObjectFactory();
    }
}
