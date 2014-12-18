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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.legstar.coxb.CobolContext;
import com.legstar.coxb.ICobolArrayZonedDecimalBinding;
import com.legstar.coxb.ICobolZonedDecimalBinding;
import com.legstar.coxb.convert.CobolConversionException;
import com.legstar.coxb.convert.ICobolZonedDecimalConverter;
import com.legstar.coxb.host.HostContext;
import com.legstar.coxb.host.HostData;
import com.legstar.coxb.host.HostException;

/**
 * This is a concrete implementation of marshal/unmarshal operations of java
 * numerics to cobol zoned decimals.
 * <p/>
 * Zoned decimals, also known as external decimals, are defined with PIC 9(n) or
 * PIC S9(n) with the implicit or explicit DISPLAY usage. They contain character
 * representation of digits and signs apart from a special case where the
 * numeric is defined as PIC S9(n) without the SIGN IS SEPARATE clause. In this
 * case, the sign info shares a byte with one of the digits (either the first
 * one if SIGN IS LEADING or the last one if SIGN is TRAILING).
 * <p/>
 * This version does not use code pages anymore when converting from host to
 * java. Using the Charset conversion is very CPU intensive. Rather we use a
 * limited set of digits, either EBCDIC or ASCII.
 * 
 */
public class CobolZonedDecimalSimpleConverter extends CobolSimpleConverter
        implements ICobolZonedDecimalConverter {

    /**
     * @param cobolContext the Cobol compiler parameters in effect
     */
    public CobolZonedDecimalSimpleConverter(final CobolContext cobolContext) {
        super(cobolContext);
    }

    /** {@inheritDoc} */
    public int toHost(final ICobolZonedDecimalBinding ce,
            final byte[] hostTarget, final int offset) throws HostException {
        int newOffset = 0;
        try {
            newOffset = toHostSingle(ce.getBigDecimalValue(),
                    ce.getByteLength(), ce.getTotalDigits(),
                    ce.getFractionDigits(), ce.isSigned(), ce.isSignSeparate(),
                    ce.isSignLeading(), hostTarget, offset, getCobolContext()
                            .getHostIntegerSigns());
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int toHost(final ICobolArrayZonedDecimalBinding ce,
            final byte[] hostTarget, final int offset, final int currentOccurs)
            throws HostException {
        int newOffset = offset;
        try {
            for (BigDecimal javaSource : ce.getBigDecimalList()) {
                newOffset = toHostSingle(javaSource, ce.getItemByteLength(),
                        ce.getTotalDigits(), ce.getFractionDigits(),
                        ce.isSigned(), ce.isSignSeparate(), ce.isSignLeading(),
                        hostTarget, newOffset, getCobolContext()
                                .getHostIntegerSigns());
            }
            /* If necessary, fill in the array with missing items */
            for (int i = ce.getBigDecimalList().size(); i < currentOccurs; i++) {
                newOffset = toHostSingle(BigDecimal.ZERO,
                        ce.getItemByteLength(), ce.getTotalDigits(),
                        ce.getFractionDigits(), ce.isSignSeparate(),
                        ce.isSignLeading(), ce.isSigned(), hostTarget,
                        newOffset, getCobolContext().getHostIntegerSigns());
            }
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int fromHost(final ICobolZonedDecimalBinding ce,
            final byte[] hostSource, final int offset) throws HostException {
        int newOffset = offset;
        try {
            BigDecimal javaDecimal = fromHostSingle(ce.getByteLength(),
                    ce.getTotalDigits(), ce.getFractionDigits(), ce.isSigned(),
                    ce.isSignSeparate(), ce.isSignLeading(), hostSource,
                    newOffset, getCobolContext().getHostIntegerSigns());
            ce.setBigDecimalValue(javaDecimal);
            newOffset += ce.getByteLength();
        } catch (CobolConversionException e) {
            throwHostException(ce, e);
        }
        return newOffset;
    }

    /** {@inheritDoc} */
    public int fromHost(final ICobolArrayZonedDecimalBinding ce,
            final byte[] hostSource, final int offset, final int currentOccurs)
            throws HostException {
        List < BigDecimal > lArray = new ArrayList < BigDecimal >();
        int newOffset = offset;
        try {
            for (int i = 0; i < currentOccurs; i++) {
                BigDecimal javaDecimal = fromHostSingle(ce.getItemByteLength(),
                        ce.getTotalDigits(), ce.getFractionDigits(),
                        ce.isSigned(), ce.isSignSeparate(), ce.isSignLeading(),
                        hostSource, newOffset, getCobolContext()
                                .getHostIntegerSigns());
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
     * Converts a Java BigDecimal to a host zoned decimal.
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
     * @param hostIntegerSigns the integer characters in the target host charset
     * @throws CobolConversionException if conversion fails
     */
    public static final int toHostSingle(final BigDecimal javaDecimal,
            final int cobolByteLength, final int totalDigits,
            final int fractionDigits, final boolean isSigned,
            final boolean isSignSeparate, final boolean isSignLeading,
            final byte[] hostTarget, final int offset,
            final byte[] hostIntegerSigns) throws CobolConversionException {

        /* Check that we are still within the host target range */
        int lastOffset = offset + cobolByteLength;
        if (lastOffset > hostTarget.length) {
            throw (new CobolConversionException(
                    "Attempt to write past end of host source buffer",
                    new HostData(hostTarget), offset, cobolByteLength));
        }

        /* Leave the first host char available for sign if needed. */
        int iTarget = offset
                + ((isSigned && isSignSeparate && isSignLeading) ? 1 : 0);

        int intHostDigits = totalDigits - fractionDigits;

        /* Process the java decimal character by character. */
        char[] source = new char[] { '0' };
        /* Fraction digits in the java decimal. */
        int fractionJavaDigits = 0;
        /* Integer digits in the java decimal. */
        int intJavaPrecision = 0;
        /* Only case where java will explicitly contain a sign. */
        boolean isNegative = false;

        if (javaDecimal != null) {
            source = javaDecimal.toPlainString().toCharArray();
            fractionJavaDigits = javaDecimal.scale();
            intJavaPrecision = javaDecimal.precision();
            isNegative = javaDecimal.signum() == -1;
        }

        /* Evaluate the java entire and fractional digits we got. */
        int totalJavaDigits = (intJavaPrecision > fractionJavaDigits) ? intJavaPrecision
                : fractionJavaDigits + 1;

        /* Java decimal is too large. */
        if (totalJavaDigits > totalDigits) {
            throw new CobolConversionException(
                    "BigDecimal value too large for target Cobol field",
                    new HostData(hostTarget), offset, cobolByteLength);
        }

        int intJavaDigits = totalJavaDigits - fractionJavaDigits;

        /* Truncate left java digits if they won't fit in the host. */
        int iSource = (intJavaDigits > intHostDigits) ? (intJavaDigits - intHostDigits)
                : 0;

        /* Skip java sign for now, it will be processed at the end. */
        if (isNegative) {
            iSource++;
        }

        /* Place integer part, left padding with zeroes if needed */
        for (int i = 0; i < intHostDigits; i++) {
            if (i < (intHostDigits - intJavaDigits)) {
                hostTarget[iTarget] = hostIntegerSigns[0];
            } else {
                hostTarget[iTarget] = hostIntegerSigns[source[iSource]
                        - (int) '0'];
                iSource++;
            }
            iTarget++;
        }

        /* Skip the java decimal point */
        iSource++;

        /* Place fraction part, right padding with zeroes if needed */
        for (int i = 0; i < fractionDigits; i++) {
            if (i >= fractionJavaDigits) {
                hostTarget[iTarget] = hostIntegerSigns[0];
            } else {
                hostTarget[iTarget] = hostIntegerSigns[source[iSource]
                        - (int) '0'];
                iSource++;
            }
            iTarget++;
        }

        /*
         * If the sign is separate and trailing we need to advance one more
         * position
         */
        if (isSigned && isSignSeparate && !isSignLeading) {
            iTarget++;
        }

        /*
         * Place the sign. It can be separate or overpunched into the first or
         * last byte.
         */
        if (isSigned) {
            if (isSignSeparate) {
                if (isSignLeading) {
                    if (isNegative) {
                        hostTarget[offset] = hostIntegerSigns[13];
                    } else {
                        hostTarget[offset] = hostIntegerSigns[12];
                    }
                } else {
                    if (isNegative) {
                        hostTarget[iTarget - 1] = hostIntegerSigns[13];
                    } else {
                        hostTarget[iTarget - 1] = hostIntegerSigns[12];
                    }
                }
            } else {
                if (isSignLeading) {
                    if (isNegative) {
                        hostTarget[offset] = (byte) (hostTarget[offset] - 0x20);
                    } else {
                        hostTarget[offset] = (byte) (hostTarget[offset] - 0x30);
                    }
                } else {
                    if (isNegative) {
                        hostTarget[iTarget - 1] = (byte) (hostTarget[iTarget - 1] - 0x20);
                    } else {
                        hostTarget[iTarget - 1] = (byte) (hostTarget[iTarget - 1] - 0x30);
                    }
                }
            }
        }

        return iTarget;
    }

    /**
     * Converts a host packed decimal to a Java BigDecimal.
     * 
     * @param cobolByteLength host byte length
     * @param totalDigits Cobol element total number of digits
     * @param fractionDigits Cobol element number of fractional digits
     * @param isSigned Cobol element is signed or unsigned
     * @param isSignSeparate Cobol element sign occupies own byte
     * @param isSignLeading Cobol element sign in first byte
     * @param hostSource source host buffer
     * @param offset offset in source host buffer
     * @param hostIntegerSigns the integer characters in the target host charset
     * @return offset after host buffer is read
     * @throws CobolConversionException if conversion fails
     */
    public static final BigDecimal fromHostSingle(final int cobolByteLength,
            final int totalDigits, final int fractionDigits,
            final boolean isSigned, final boolean isSignSeparate,
            final boolean isSignLeading, final byte[] hostSource,
            final int offset, final byte[] hostIntegerSigns)
            throws CobolConversionException {

        int lastOffset = offset + cobolByteLength;

        /*
         * Check that we are still within the host source range. If not,
         * consider the host optimized its payload by truncating trailing nulls
         * in which case, we just need to initialize and return.
         */
        if (lastOffset > hostSource.length) {
            return new BigDecimal(0).setScale(fractionDigits);
        }
        if (lastOffset < 1) {
            throw (new CobolConversionException("Invalid host byte length",
                    new HostData(hostSource), offset, cobolByteLength));
        }

        int sourceSize = totalDigits;
        if (isSigned) {
            sourceSize++;
        }
        char[] workDecimal = new char[sourceSize];

        /*
         * Transfer source bytes to work byte array. The objective is that the
         * work byte array contains only valid digits ready for code page
         * translation. The first and last source bytes might be sign bytes.
         * These happen for signed decimals with overpunch sign (not separate).
         * Such a byte encodes both the sign (high order 4 bits) and a digit
         * (low order 4 bits) so it results in 2 bytes in the work byte array.
         */

        /*
         * reserve first java character for sign when it cannot be determined
         * right away from the host byte.
         */
        int i = (isSigned && (!isSignLeading || (isSignLeading && !isSignSeparate))) ? 1
                : 0;
        byte hostByte;
        for (int iSource = offset; iSource < lastOffset; iSource++) {
            hostByte = hostSource[iSource];
            if (isSigned) {
                if (iSource == offset && isSignLeading && !isSignSeparate) {
                    setFromOverPunch(workDecimal, hostByte, i, hostIntegerSigns);
                    i++;
                    continue;
                }

                if (iSource == lastOffset - 1 && !isSignLeading) {
                    if (isSignSeparate) {
                        workDecimal[0] = toJavaChar(hostByte, hostIntegerSigns);
                    } else {
                        setFromOverPunch(workDecimal, hostByte, i,
                                hostIntegerSigns);
                    }
                    i++;
                    continue;
                }

            }
            workDecimal[i] = toJavaChar(hostByte, hostIntegerSigns);
            i++;
        }

        /* Turn the char array into a decimal with the required scale */
        BigDecimal result;
        try {
            result = new BigDecimal(workDecimal);
        } catch (NumberFormatException e) {
            throw (new CobolConversionException(
                    "Host data contains a byte that is not a valid zoned"
                            + " decimal byte", new HostData(hostSource),
                    offset, cobolByteLength));
        }
        if (fractionDigits == 0) {
            return result;
        } else {
            return result.movePointLeft(fractionDigits);
        }
    }

    /**
     * Sets the java sign as the first character, based on the zoned decimal
     * byte that holds the sign as an overpunch character the second half byte
     * of the overpunch byte is the actual digit.
     * 
     * @param workDecimal the Java decimal being built
     * @param hostByte the host byte with overpunch sign
     * @param i the current index in the java decimal being built
     */
    protected static void setFromOverPunch(char[] workDecimal, byte hostByte,
            int i, final byte[] hostIntegerSigns) {
        int signCode = hostByte & 0xF0;
        if (signCode == 0xd0) {
            workDecimal[0] = '-';
        } else {
            workDecimal[0] = '+';
        }
        workDecimal[i] = toJavaChar((byte) ((hostByte & 0x0F) + 0xF0),
                hostIntegerSigns);
    }

    /**
     * Lookup a host numeric character and translate to java.
     * <p/>
     * A host white space is translated to a java zero digit. On the host,
     * numerics are frequently left padded with spaces but java does not like
     * that.
     * <p/>
     * Similarly, host might use low values for padding which again does not
     * suit java so we switch that to zeroes.
     * 
     * @param hostByte the host numeric character
     * @param hostIntegerSigns the list of host integer characters
     * @return the corresponding java numeric character or 0 if not found
     */
    public static char toJavaChar(final byte hostByte,
            final byte[] hostIntegerSigns) {
        for (int i = 0; i < hostIntegerSigns.length; i++) {
            if (hostByte == hostIntegerSigns[i]) {
                char javaChar = HostContext.getIntegerSigns().charAt(i);
                return javaChar == ' ' ? '0' : (javaChar == '\0' ? '0'
                        : javaChar);
            }
        }
        return '\0';
    }

}
