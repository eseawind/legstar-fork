
package com.legstar.test.cixs.arraygrp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "arraygrpPort", targetNamespace = "http://cixs.test.legstar.com/arraygrp")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.legstar.test.coxb.arraygrp.ObjectFactory.class,
    com.legstar.test.cixs.arraygrp.ObjectFactory.class
})
public interface ArraygrpPort {


    /**
     * 
     * @param hostHeader
     * @param parameters
     * @return
     *     returns com.legstar.test.cixs.arraygrp.ArraygrpResponse
     * @throws ArraygrpFault
     */
    @WebMethod(action = "urn:arraygrp")
    @WebResult(name = "ArraygrpResponse", targetNamespace = "http://cixs.test.legstar.com/arraygrp", partName = "result")
    public ArraygrpResponse arraygrp(
        @WebParam(name = "ArraygrpRequest", targetNamespace = "http://cixs.test.legstar.com/arraygrp", partName = "parameters")
        ArraygrpRequest parameters,
        @WebParam(name = "ArraygrpHostHeader", targetNamespace = "http://cixs.test.legstar.com/arraygrp", header = true, partName = "hostHeader")
        ArraygrpHostHeader hostHeader)
        throws ArraygrpFault
    ;

}
