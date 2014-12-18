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
package com.legstar.coxb.convert.simple;

import com.legstar.coxb.convert.CobolConversionException;
import com.legstar.coxb.host.HostData;

import junit.framework.TestCase;

/**
 * Test the COBOL COMP-2 TYPE.
 *
 */
public class DoubleTest extends TestCase {

    /**
     * Generic conversion from java double to COBOL.
     * @param byteLength the COBOL receiving field size 
     * @param inputValue the input value as a string
     * @param expectedValue the expected value as a hex string
     */
    public static void toHost(
            final int byteLength,
            final String inputValue,
            final String expectedValue) {
        try {
            byte[] hostBytes = new byte[byteLength];
            Double javaDouble = new Double(inputValue);
            assertEquals(byteLength, CobolDoubleSimpleConverter.toHostSingle(
                    javaDouble, hostBytes, 0));
            assertEquals(expectedValue, HostData.toHexString(hostBytes));
        } catch (CobolConversionException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Generic conversion from COBOL to java double.
     * @param byteLength the COBOL receiving field size 
     * @param inputValue the input value as a hex string
     * @param expectedValue the expected value as a string
     */
    public static void fromHost(
            final int byteLength,
            final String inputValue,
            final String expectedValue) {
        try {
            byte[] hostBytes = HostData.toByteArray(inputValue);
            Double javaDouble = CobolDoubleSimpleConverter.fromHostSingle(
                    byteLength, hostBytes, 0);
            assertEquals(expectedValue, javaDouble.toString());
        } catch (CobolConversionException e) {
            fail(e.getMessage());
        }
    }

    /**
     * COMP-2 small value.
     */
    public void testToHost1234() {
        toHost(8, "+1234.0d", "434d200000000000");
    }

    /**
     * COMP-2 small value.
     */
    public void testFromHost1234() {
        fromHost(8, "434d200000000000", "1234.0");
    }

    /**
     * Case where we are past the offset. Not enough data sent from the mainframe.
     */
    public void testFromHostPastOffset() {
        try {
            byte[] hostSource = HostData.toByteArray("3616");
            Double javaDouble = CobolDoubleSimpleConverter.fromHostSingle(8, hostSource, 0);
            assertEquals("0.0", javaDouble.toString());
        } catch (CobolConversionException e) {
            fail(e.getMessage());
        }
    }

    /**
     * COMP-2 zero value.
     */
    public void testToHost0() {
        toHost(8, "+0.0d", "0000000000000000");
    }

    /**
     * COMP-2 zero value.
     */
    public void testFromHost0() {
        fromHost(8, "0000000000000000", "0.0");
    }

    /**
     * COMP-2 1 value.
     */
    public void testToHost1() {
        toHost(8, "+1.0d", "4110000000000000");
    }

    /**
     * COMP-2 1 value.
     */
    public void testFromHost1() {
        fromHost(8, "4110000000000000", "1.0");
    }

    /**
     * COMP-2 no exponent value.
     */
    public void testToHost345006p5678() {
        toHost(8, "+345006.5678d", "45543ae915b573e8");
    }

    /**
     * COMP-2  no exponent value.
     */
    public void testFromHost345006p5678() {
        fromHost(8, "45543ae915b573eb", "345006.56779999996");
    }

    /**
     * COMP-2  exponent value.
     */
    public void testToHost798p20067em16() {
        toHost(8, "+798.20067e-16d", "361677a4590fab68");
    }

    /**
     * COMP-2  exponent value.
     */
    public void testFromHost798p20067em16() {
        fromHost(8, "361677a4590fab68", "7.982006699999995E-14");
    }

    /**
     * COMP-2  large exponent value.
     */
    public void testToHost3p40282347ep38() {
        toHost(8, "3.40282347e+38d", "60ffffff048ff9e8");
    }

    /**
     * COMP-2  large exponent value.
     */
    public void testFromHost3p40282347ep38() {
        fromHost(8, "60ffffff048ff9e8", "3.40282347E38");
    }

    /**
     * COMP-2 negative value.
     */
    public void testToHostm5p670078Em14() {
        toHost(8, "-5.670078E-14d", "b5ff5b8c70649ed0");
    }

    /**
     * COMP-2 negative value.
     */
    public void testFromHostm5p670078Em14() {
        fromHost(8, "b5ff5b8c70649ed0", "-5.670078E-14");
    }

    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost1() {
        toHost(8, "0.5160421914381665d", "40841b574f9545d0");
        fromHost(8, "40841b574f9545cd", "0.5160421914381664");
    }
    
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost2() {
        toHost(8, "0.3767408804881589d", "4060721720c34c90");
        fromHost(8, "4060721720c34c90", "0.3767408804881589");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost3() {
        toHost(8, "0.5748594107197614d", "409329fc80d6d648");
        fromHost(8, "409329fc80d6d646", "0.5748594107197613");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost4() {
        toHost(8, "0.6001057816958459d", "4099a08852863040");
        fromHost(8, "4099a0885286303d", "0.6001057816958458");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost5() {
        toHost(8, "0.7850369597199326d", "40c8f82ea425fc38");
        fromHost(8, "40c8f82ea425fc39", "0.7850369597199326");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost6() {
        toHost(8, "0.2998016949252067d", "404cbfcdcafd37c0");
        fromHost(8, "404cbfcdcafd37c0", "0.2998016949252067");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost7() {
        toHost(8, "0.7788391667528523d", "40c76200ee0c21d0");
        fromHost(8, "40c76200ee0c21d3", "0.7788391667528523");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost8() {
        toHost(8, "0.8158852356638823d", "40d0dddad4773358");
        fromHost(8, "40d0dddad477335a", "0.8158852356638823");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost9() {
        toHost(8, "0.4364272923152456d", "406fb9b2f3936868");
        fromHost(8, "406fb9b2f393686a", "0.4364272923152456");
    }
    /**
     * COMP-2 Amit cases.
     */
    public void testAmitPrecisionLost10() {
        toHost(8, "0.6062248297052384d", "409b318ce99b6f60");
        fromHost(8, "409b318ce99b6f61", "0.6062248297052384");
    }
}
