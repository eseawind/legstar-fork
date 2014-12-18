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
package com.legstar.proxy.invoke.jaxws;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.legstar.proxy.invoke.DirectOperationProxy;
import com.legstar.proxy.invoke.IOperationProxy;
import com.legstar.proxy.invoke.IProxyInvoker;
import com.legstar.proxy.invoke.ReflectOperationProxy;

/**
 * Centralize data for Cultureinfo test case.
 * 
 */
public class CultureinfoJaxwsCases extends TestCase {

    /**
     * @return a valid proxy configuration
     */
    private static Map < String, String > getConfig() {
        Map < String, String > config = new HashMap < String, String >();

        config.put(IProxyInvoker.PROXY_INVOKER_CLASS_NAME_PROPERTY,
                "com.legstar.proxy.invoke.jaxws.WebServiceInvoker");

        config.put(WebServiceInvoker.WSDL_URL_PROPERTY,
                "http://localhost:8080/legstar-test-cultureinfo/getinfo?wsdl");
        config.put(WebServiceInvoker.WSDL_TARGET_NAMESPACE_PROPERTY,
                "http://cultureinfo.cases.test.xsdc.legstar.com/");
        config.put(WebServiceInvoker.WSDL_SERVICE_NAME_PROPERTY,
                "CultureInfoImplService");
        config.put(WebServiceInvoker.WSDL_PORT_NAME_PROPERTY,
                "CultureInfoImplPort");
        return config;
    }

    /**
     * @return a complete configuration that result in direct proxying.
     */
    public static Map < String, String > getDirectConfig() {
        Map < String, String > config = getReflectConfig();
        config.put(
                DirectOperationProxy.REQUEST_TRANSFORMERS_CLASS_NAME_PROPERTY,
                "com.legstar.test.coxb.cultureinfo.bind.GetInfoTransformers");
        config.put(
                DirectOperationProxy.RESPONSE_TRANSFORMERS_CLASS_NAME_PROPERTY,
                "com.legstar.test.coxb.cultureinfo.bind.GetInfoResponseTransformers");
        config.put(IOperationProxy.HOST_CHARSET_PROPERTY, "IBM01147");
        return config;
    }

    /**
     * @return a complete configuration that result in reflection proxying.
     */
    public static Map < String, String > getReflectConfig() {
        Map < String, String > config = getConfig();
        config.put(ReflectOperationProxy.REQUEST_JAXB_TYPE_PROPERTY, "GetInfo");
        config.put(ReflectOperationProxy.REQUEST_JAXB_PACKAGE_NAME_PROPERTY,
                "com.legstar.test.coxb.cultureinfo");
        config.put(ReflectOperationProxy.RESPONSE_JAXB_TYPE_PROPERTY,
                "GetInfoResponse");
        config.put(ReflectOperationProxy.RESPONSE_JAXB_PACKAGE_NAME_PROPERTY,
                "com.legstar.test.coxb.cultureinfo");
        return config;
    }

}
