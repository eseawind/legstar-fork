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
package com.legstar.cobol.gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * This class is used to determine a valid Cobol name. A valid cobol name is
 * less than 30 characters long where characters are restricted to:
 * 
 * <PRE>
 * A through Z, a through z, 0 through 9, - (hyphen).
 * </PRE>
 * 
 * Furthermore, such a name must not be identical to one of the Cobol reserved
 * words. For simplicity (this is not a Cobol requirement) we also make sure the
 * proposed Cobol name is unique within the context of this name resolver.
 * 
 */
public class CobolNameResolver {

    /** Serial ID. */
    private static final long serialVersionUID = 1L;

    /** The cobol reserved words substitution file name. */
    private static final String RWS_FILE_NAME = "cobolrws.properties";

    /** List of COBOL reserved words. */
    private Properties _reservedWords;

    /** Valid cobol characters table. */
    private static char[] mC = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', '-' };

    /** Maximum size of a Cobol item name. */
    public static final int MAX_COBOLNAME_LEN = 30;

    /**
     * For reserved words with no substitution this prefex will be prepended to
     * ensure cobol var is not reserved.
     */
    public static final String RESERVED_PREFIX = "R-";

    /** Names already used. */
    private List < String > _usedNames = new ArrayList < String >();

    /**
     * Get a valid cobol name that is guaranteed to be unique within the context
     * of this resolver.
     * 
     * @param hint a potential content for the cobol name
     * @return a valid cobol name
     */
    public String getUniqueName(final String hint) {
        String cobolName = getName(hint);

        /* Make sure this is a unique name otherwise build a unique one */
        cobolName = makeUnique(cobolName);

        /* Keep it in the used list */
        _usedNames.add(cobolName);

        return cobolName;

    }

    /**
     * Get a valid cobol name.
     * 
     * @param hint a potential content for the cobol name
     * @return a valid cobol name
     */
    public String getName(final String hint) {

        /* Make sure proposed name only contains valid cobol characters */
        String cobolName = switchCharacters(hint);

        /* Trim the proposed name if it is too long */
        if (cobolName.length() > MAX_COBOLNAME_LEN) {
            cobolName = cobolName.substring(0, MAX_COBOLNAME_LEN);
            /* After trimming, the last character might now be invalid */
            int i = MAX_COBOLNAME_LEN - 1;
            while (!isValidLast(cobolName.charAt(i))) {
                i--;
            }
            cobolName = cobolName.substring(0, i + 1);
        }

        /* Check if this is a reserved word and get a substitution */
        String subst = getSubstituteWord(cobolName.toUpperCase(Locale
                .getDefault()));
        if (subst != null) {
            if (subst.length() == 0) {
                cobolName = RESERVED_PREFIX + cobolName;
            } else {
                cobolName = subst;
            }
        }

        return cobolName;

    }

    /**
     * Check a cobol name against the list of reserved words.
     * 
     * @param cobolName the cobol name to check
     * @return null if this is not a reserved word, empty string if it is a
     *         reserved word but there are no proposed substitutions otherwise
     *         the proposed substitution
     */
    protected String getSubstituteWord(final String cobolName) {
        if (_reservedWords == null) {
            _reservedWords = loadProperties(RWS_FILE_NAME);
        }
        return _reservedWords.getProperty(cobolName);
    }

    /**
     * Creates a new string built from each valid character from name.
     * 
     * @param name the proposed name with potentially invalid characters
     * @return a name that is guaranteed to contain only valid characters
     */
    protected String switchCharacters(final String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            boolean valid = false;
            for (int j = 0; j < mC.length; j++) {
                if (name.charAt(i) == mC[j]) {
                    sb.append(name.charAt(i));
                    valid = true;
                    break;
                }
            }
            /*
             * Ignore invalid characters unless it is an underscore
             */
            if (!valid && name.charAt(i) == '_') {
                sb.append('-');
            }
        }

        /* Check first and last characters */
        while (sb.length() > 0 && !isValidFirst(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        while (sb.length() > 0 && !isValidLast(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Determines if this is a valid first character in a cobol word.
     * 
     * @param c the character to check
     * @return true if this is a valid first character
     */
    protected boolean isValidFirst(final char c) {
        if (c == '-' || Character.isDigit(c)) {
            return false;
        }
        return true;
    }

    /**
     * Determines if this is a valid last character in a cobol word.
     * 
     * @param c the character to check
     * @return true if this is a valid last character
     */
    protected boolean isValidLast(final char c) {
        if (c == '-') {
            return false;
        }
        return true;
    }

    /**
     * Look up a proposed name in the used names list. If it is already used a
     * new name built with a numeric suffix is proposed.
     * 
     * @param name the name to check for unicity
     * @return a unique name with the context of this resolver
     */
    protected String makeUnique(final String name) {
        int sfx = 0;
        String s = name;
        while (_usedNames.contains(s)) {
            String suffix = Integer.toString(sfx);
            if ((name + suffix).length() > MAX_COBOLNAME_LEN) {
                s = name.substring(0, MAX_COBOLNAME_LEN - suffix.length())
                        + suffix;
            } else {
                s = name + suffix;
            }
            sfx++;
        }
        return s;
    }

    /**
     * Loads a properties file from a location.
     * <p/>
     * The location could be within the classpath. A first attempt using the
     * properties class loader is made. If that fails, the context class loader
     * is tried. Finally if all that fails, a local file is tried.
     * 
     * @param location the properties content location
     * @return a loaded Properties instance
     * @throws IOException if content cannot be located
     */
    protected Properties loadProperties(final String location) {
        InputStream stream = null;
        try {
            Properties props = new Properties();
            stream = getClass().getResourceAsStream(location);
            if (stream == null) {
                stream = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(location);
            }
            if (stream == null) {
                stream = new FileInputStream(new File(location));
            }
            props.load(stream);
            return props;
        } catch (IOException e) {
            throw new MissingResourceException("Unable to locate resource "
                    + location, "InputStream", location);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // Eat this one
                }
            }
        }

    }
}
