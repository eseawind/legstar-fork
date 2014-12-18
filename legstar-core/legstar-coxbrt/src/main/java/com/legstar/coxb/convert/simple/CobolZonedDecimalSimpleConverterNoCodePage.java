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

import com.legstar.coxb.CobolContext;
import com.legstar.coxb.ICobolArrayZonedDecimalBinding;
import com.legstar.coxb.ICobolZonedDecimalBinding;
import com.legstar.coxb.convert.ICobolZonedDecimalConverter;
import com.legstar.coxb.convert.CobolConversionException;
import com.legstar.coxb.host.HostData;
import com.legstar.coxb.host.HostException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a concrete implementation of marshal/unmarshal operations of java 
 * numerics to cobol zoned decimals.
 *
 * @author Fady Moussallam
 * @deprecated
 */
public class CobolZonedDecimalSimpleConverterNoCodePage
extends CobolSimpleConverter
implements ICobolZonedDecimalConverter {

    /** Ebcdic code point for plus sign. */
    private static final byte PLUS_EBCDIC = 0x4E; 

    /** Ebcdic code point for minus sign. */
    private static final byte MINUS_EBCDIC = 0x60; 

    /**
     * @param cobolContext the Cobol compiler parameters in effect
     */
    public CobolZonedDecimalSimpleConverterNoCodePage(
            final CobolContext cobolContext) {
        super(cobolContext);
    }

    /** {@inheritDoc} */
    public int toHost(
            final ICobolZonedDecimalBinding ce,
            final byte[] hostTarget,
            final int offset) throws HostException {
        int newOffset = 0;
        try {
            newOffset = toHostSingle(ce.getBigDecimalValue(),
                    ce.getByteLength(),
                    ce.getTotalDigits(),
                    ce.getFractionDigits(),
                    ce.isSigned(),
                    ce.isSignSeparate(),
                    ce.isSignLeading(),
                    hostTarget,
                    offset);
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int toHost(
            final ICobolArrayZonedDecimalBinding ce,
            final byte[] hostTarget,
            final int offset,
            final int currentOccurs)
    throws HostException {
        int newOffset = offset;
        try {
            for (BigDecimal javaSource : ce.getBigDecimalList()) {
                newOffset = toHostSingle(javaSource,
                        ce.getItemByteLength(),
                        ce.getTotalDigits(),
                        ce.getFractionDigits(),
                        ce.isSigned(),
                        ce.isSignSeparate(),
                        ce.isSignLeading(),
                        hostTarget,
                        newOffset);
            }
            /* If necessary, fill in the array with missing items */
            for (int i = ce.getBigDecimalList().size();
            i < currentOccurs; i++) {
                newOffset = toHostSingle(BigDecimal.ZERO,
                        ce.getItemByteLength(),
                        ce.getTotalDigits(),
                        ce.getFractionDigits(),
                        ce.isSignSeparate(),
                        ce.isSignLeading(),
                        ce.isSigned(),
                        hostTarget,
                        newOffset);
            }
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int fromHost(
            final ICobolZonedDecimalBinding ce,
            final byte[] hostSource,
            final int offset)
    throws HostException {
        int newOffset = offset;
        try {
            BigDecimal javaDecimal = fromHostSingle(ce.getByteLength(),
                    ce.getTotalDigits(),
                    ce.getFractionDigits(),
                    ce.isSigned(),
                    ce.isSignSeparate(),
                    ce.isSignLeading(),
                    hostSource,
                    newOffset);
            ce.setBigDecimalValue(javaDecimal);
            newOffset += ce.getByteLength();
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int fromHost(
            final ICobolArrayZonedDecimalBinding ce,
            final byte[] hostSource,
            final int offset,
            final int currentOccurs)
    throws HostException {
        List < BigDecimal > lArray = new ArrayList < BigDecimal >();
        int newOffset = offset;
        try {
            for (int i = 0; i < currentOccurs; i++) {
                BigDecimal javaDecimal = fromHostSingle(ce.getItemByteLength(),
                        ce.getTotalDigits(),
                        ce.getFractionDigits(),
                        ce.isSigned(),
                        ce.isSignSeparate(),
                        ce.isSignLeading(),
                        hostSource,
                        newOffset);
                lArray.add(javaDecimal);
                newOffset += ce.getItemByteLength();
            }
            ce.setBigDecimalList(lArray);
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /**
     *  Converts a Java BigDecimal to a host zoned decimal.
     * 
     * @param javaDecimal java decimal to convert
     * @param cobolByteLength host byte length
     * @param totalDigits Cobol element total number of digits
     * @param fractionDigits Cobol element number of fractional digits
     * @param isSigned Cobol element is signed or unsigned
     * @param isSignSeparate Cobol element sign occupies own byte
     * @param isSignLeading Cobol element sign in first byte
     * @param hostTarget target host buffer
     * @param offset offset in target host buffer
     * @return offset after host buffer is updated
     * @throws CobolConversionException if conversion fails
     */
    public static final int toHostSingle(
            final BigDecimal javaDecimal,
            final int cobolByteLength,
            final int totalDigits,
            final int fractionDigits,
            final boolean isSigned,
            final boolean isSignSeparate,
            final boolean isSignLeading,
            final byte[] hostTarget,
            final int offset)
    throws CobolConversionException {

        /* Check that we are still within the host target range */
        int lastOffset = offset + cobolByteLength;
        if (lastOffset > hostTarget.length) {
            throw (new CobolConversionException(
                    "Attempt to write past end of host source buffer",
                    new HostData(hostTarget), offset, cobolByteLength));
        }

        // Get a string representation of the decimal value
        String sDecimal;
        if (javaDecimal == null) {
            sDecimal = "0";
        } else {
            sDecimal = javaDecimal.toString();
            /* if the Java decimal has a different scale than target cobol 
             * field, adjust scale */
            if (javaDecimal.scale() != fractionDigits) {
                sDecimal = javaDecimal.setScale(
                        fractionDigits, BigDecimal.ROUND_DOWN).toString();
            }
        }

        /* Determine the number of digits that the java decimal holds */
        int javaDigits = 0;
        for (int i = 0; i < sDecimal.length(); i++) {
            if (Character.isDigit(sDecimal.charAt(i))) {
                javaDigits++;
            }
        }

        if (javaDigits > totalDigits) {
            throw (new CobolConversionException(
                    "BigDecimal value too large for target Cobol field",
                    new HostData(hostTarget), offset, cobolByteLength));
        }


        /* Number of digits that are needed to pad the java value if it has
         * less digits than the target cobol field */
        int pad = totalDigits - javaDigits;

        int iTarget = offset;   /* points to current byte in host data */

        /* Reserve first byte of target for sign if needed */
        if (isSigned && isSignSeparate && isSignLeading) {
            iTarget++;
        }

        /* Pad to the left with zeroes as necessary */
        for (int i = 0; i < pad; i++) {
            hostTarget[iTarget] = (byte) 0xF0;
            iTarget++;
        }

        /* Preposition all digits without consideration for sign */
        for (int i = 0; i < sDecimal.length(); i++) {
            char sC = sDecimal.charAt(i);
            if (Character.isDigit(sC)) {
                hostTarget[iTarget] =
                    (byte) (16 * 0x0F + Character.digit(sC, 10));
                iTarget++;
            }
        }

        /* Position the sign either separately or imbedded */
        if (isSigned) {
            if (isSignSeparate) {
                if (isSignLeading) {
                    if (sDecimal.charAt(0) == '-') {
                        hostTarget[offset] = MINUS_EBCDIC;
                    } else {
                        hostTarget[offset] = PLUS_EBCDIC;
                    }
                } else {
                    if (sDecimal.charAt(0) == '-') {
                        hostTarget[iTarget] = MINUS_EBCDIC;
                    } else {
                        hostTarget[iTarget] = PLUS_EBCDIC;
                    }
                    iTarget++;
                }
            } else {
                if (isSignLeading) {
                    if (sDecimal.charAt(0) == '-') {
                        hostTarget[offset] = (byte) (hostTarget[offset] - 0x20);
                    } else {
                        hostTarget[offset] = (byte) (hostTarget[offset] - 0x30);
                    }
                } else {
                    if (sDecimal.charAt(0) == '-') {
                        hostTarget[iTarget - 1] =
                            (byte) (hostTarget[iTarget - 1] - 0x20);
                    } else {
                        hostTarget[iTarget - 1] =
                            (byte) (hostTarget[iTarget - 1] - 0x30);
                    }
                }
            }
        }

        return iTarget;
    }

    /** Converts a host packed decimal to a Java BigDecimal.
     * 
     * @param cobolByteLength host byte length
     * @param totalDigits Cobol element total number of digits
     * @param fractionDigits Cobol element number of fractional digits
     * @param isSigned Cobol element is signed or unsigned
     * @param isSignSeparate Cobol element sign occupies own byte
     * @param isSignLeading Cobol element sign in first byte
     * @param hostSource source host buffer
     * @param offset offset in source host buffer
     * @return offset after host buffer is read
     * @throws CobolConversionException if conversion fails
     */
    public static final BigDecimal fromHostSingle(
            final int cobolByteLength,
            final int totalDigits,
            final int fractionDigits,
            final boolean isSigned,
            final boolean isSignSeparate,
            final boolean isSignLeading,
            final byte[] hostSource,
            final int offset)
    throws CobolConversionException {

        /* To initialize the BigDecimal, we construct a String that represents
         * the decimal value held in the Cobol zoned decimal */
        StringBuffer sDecimal = new StringBuffer();

        int lastOffset = offset + cobolByteLength;

        /* Check that we are still within the host source range.
         * If not, consider the host optimized its payload by truncating
         * trailing nulls in which case, we just need to initialize and return. */
        if (lastOffset > hostSource.length) {
            return new BigDecimal(0);
        }
        if (lastOffset < 1) {
            throw (new CobolConversionException(
                    "Invalid host byte length",
                    new HostData(hostSource), offset, cobolByteLength));
        }

        /* First determine the sign of this decimal */
        if (isSigned) {
            /* If sign is separate check leading or trailing byte for a minus
             * sign */
            if (isSignSeparate) {
                if (isSignLeading)  {
                    if (hostSource[offset] == MINUS_EBCDIC) {
                        sDecimal.append('-');
                    }
                } else {
                    if (hostSource[lastOffset - 1] == MINUS_EBCDIC) {
                        sDecimal.append('-');
                    }
                }
            } else {
                /* If sign is imbedded check leading or trailing byte for a
                 *  minus sign */
                int s = (isSignLeading) ? (hostSource[offset] & 0xF0)
                        : (hostSource[lastOffset - 1]  & 0xF0);
                if (s == 0xd0) {
                    sDecimal.append('-');
                } else {
                    if (s != 0xc0 && s != 0xf0) {
                        throw (new CobolConversionException(
                                "Host data sign byte is not a valid zoned decimal byte",
                                new HostData(hostSource), offset, cobolByteLength));
                    }
                }
            }
        }

        /* Each byte holds 1 digit */
        int integerPart = 0;
        for (int i = offset; i < lastOffset; i++) {

            /* Ignore the potential leading sign */
            if ((i == offset)
                    &&
                    isSigned && isSignSeparate && isSignLeading) {
                continue;
            }

            /* Ignore the potential trailing sign */
            if ((i == (lastOffset - 1))
                    &&
                    isSigned && isSignSeparate && !isSignLeading) {
                continue;
            }

            String sByte = Integer.toHexString(
                    hostSource[i] & 0xFF | 0x100).substring(1, 3);

            /* If the integer part is exhausted, place a decimal point */
            if (integerPart == (totalDigits - fractionDigits)) {
                sDecimal.append('.');
            }

            if (!Character.isDigit(sByte.charAt(1))) {
                throw (new CobolConversionException(
                        "Host data contains a byte that is not a valid zoned"
                        + " decimal byte",
                        new HostData(hostSource), offset, cobolByteLength));
            }

            sDecimal.append(sByte.charAt(1));
            integerPart += 1;
        }

        return new BigDecimal(sDecimal.toString());
    }
}
