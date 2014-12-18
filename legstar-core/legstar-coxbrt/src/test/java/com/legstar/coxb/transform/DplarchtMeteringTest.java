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
package com.legstar.coxb.transform;

import junit.framework.TestCase;

import com.legstar.coxb.host.HostData;
import com.legstar.test.coxb.DplarchtCases;
import com.legstar.test.coxb.dplarcht.Dfhcommarea;


/**
 * This class is useful for performance testing with JMeter.
 * It simulates 3 different payload sizes by varying the number of items in
 * a variable size array.
 *
 */
public class DplarchtMeteringTest extends TestCase {
    
    /** Number of files in variable size array. */
    private int mFiles;
    
    /** Host data for the given number of files. */
    private byte[] mHostBytes;
    
    /**
     * By default, considers empty variable size array.
     */
    public DplarchtMeteringTest() {
        this("0");
    }
    
    /**
     * @param label JMeter passes a label that we use as a parameter for this test 
     */
    public DplarchtMeteringTest(final String label) {
        super(label);
        try {
            mFiles = Integer.parseInt(label);
        } catch (NumberFormatException e) {
            mFiles = 0;
        }
        mHostBytes = HostData.toByteArray(DplarchtCases.getHostBytesHexFiles(mFiles));
    }
    
    /**
     * DPLARCHT from Host to Java.
     */
    public void testHostToJava() {
        try {
            DplarchtTransformers transformers = new DplarchtTransformers();
            Dfhcommarea dfhcommarea = (Dfhcommarea) transformers.toJava(
                    getHostBytes());
            DplarchtCases.checkJavaObjectFiles(getFiles(), dfhcommarea);
        } catch (HostTransformException e) {
            fail(e.getMessage());
        }
    }

    /**
     * DPLARCHT from Java to Host.
     */
    public void testJavaToHost() {
        try {
            DplarchtTransformers transformers = new DplarchtTransformers();
            byte[] hostBytes = transformers.toHost(
                    DplarchtCases.getJavaObjectFiles(getFiles()));
            assertEquals(getHostBytes().length, hostBytes.length);
        } catch (HostTransformException e) {
            fail(e.getMessage());
        }
    }

    /**
     * @return the number of files in variable size array
     */
    public int getFiles() {
        return mFiles;
    }

    /**
     * @return the host data for the given number of files
     */
    public byte[] getHostBytes() {
        return mHostBytes;
    }
}
