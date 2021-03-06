
package com.legstar.test.cixs.lsfileax;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "LsfileaeFaultInfo", targetNamespace = "http://cixs.test.legstar.com/lsfileax")
public class LsfileaeFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private LsfileaeFaultInfo faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public LsfileaeFault(String message, LsfileaeFaultInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public LsfileaeFault(String message, LsfileaeFaultInfo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.legstar.test.cixs.lsfileax.LsfileaeFaultInfo
     */
    public LsfileaeFaultInfo getFaultInfo() {
        return faultInfo;
    }

}
