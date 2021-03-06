package com.legstar.test.cixs.binnatsi;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.legstar.test.coxb.binnatsi.Dfhcommarea;
/**
 * LegStar/Jaxws Component interface.
 * Each method maps to a CICS program. 
 * 
 * This class was generated by LegStar Mainframe Web Service adapter generator.
 */
@WebService(name = "binnatsiPort",
            targetNamespace = "http://cixs.test.legstar.com/binnatsi")
public interface Binnatsi {
  
    /**
     * LegStar operation binnatsi.
     * 
     * @param request a JAXB object mapping the request
     * @param hostHeader an object mapping header parameters
     * @return a JAXB object mapping the reply
     * @throws BinnatsiFault if method fails
     */
    @WebMethod(operationName = "binnatsi", action = "urn:binnatsi")
    @WebResult(name = "Dfhcommarea",
        targetNamespace = "http://legstar.com/test/coxb/binnatsi")
    @RequestWrapper(localName = "BinnatsiRequest",
        targetNamespace = "http://cixs.test.legstar.com/binnatsi",
        className = "com.legstar.test.cixs.binnatsi.BinnatsiRequest")
    @ResponseWrapper(localName = "BinnatsiResponse",
        targetNamespace = "http://cixs.test.legstar.com/binnatsi",
        className = "com.legstar.test.cixs.binnatsi.BinnatsiResponse")
    Dfhcommarea binnatsi(
        @WebParam(name = "Dfhcommarea",
               targetNamespace = "http://legstar.com/test/coxb/binnatsi")
            Dfhcommarea request,
        @WebParam(name = "BinnatsiHostHeader", header = true, partName = "hostHeader",
                targetNamespace = "http://cixs.test.legstar.com/binnatsi")
            BinnatsiHostHeader hostHeader)
        throws BinnatsiFault;
        
}
