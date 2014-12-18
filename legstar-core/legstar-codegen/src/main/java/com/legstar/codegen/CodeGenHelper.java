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
package com.legstar.codegen;

/**
 * Provides the generator with convenience methods. The class can be 
 * passed as an instance to the velocity engine and used by templates.
 */
public class CodeGenHelper {

    /**
     * Checks a string for emptiness. This is needed because velocity
     * cannot check for nulls.
     * @param str the string to check
     * @return true if the string has a content (not empty)
     */
    public boolean isEmpty(final String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * Determines the package name of a fully qualified class name.
     * @param qualClassName class name including package
     * @param defaultPackageName a default to return if class is not qualified
     * @return the package name or default if class not qualified
     */
    public String getPackageName(
            final String qualClassName, final String defaultPackageName) {
        int idx = qualClassName.lastIndexOf('.');
        if (idx < 1) {
            return defaultPackageName;
        }
        return qualClassName.substring(0, idx);
    }

    /**
     * Get the simple class name from a fully qualified class name.
     * @param qualClassName class name including package
     * @return the last part of the fully qualified name
     */
    public String getClassName(final String qualClassName) {
        int idx = qualClassName.lastIndexOf('.');
        if (idx < 0) {
            return qualClassName;
        }
        return qualClassName.substring(idx + 1, qualClassName.length());
    }

    /**
     * Get a fully qualified class name.
     * @param packageName the package or null if none
     * @param className the class name
     * @return the class name prefixed with the package name unless there
     * is no package in which case, the class name is returned.
     */
    public String getQualClassName(final String packageName, final String className) {
        if (packageName == null || packageName.length() == 0) {
            return className;
        }
        return packageName + '.' + className;
    }
}
