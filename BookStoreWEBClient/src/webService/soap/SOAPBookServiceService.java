/**
 * SOAPBookServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService.soap;

public interface SOAPBookServiceService extends javax.xml.rpc.Service {
    public java.lang.String getSOAPBookServiceAddress();

    public webService.soap.SOAPBookService getSOAPBookService() throws javax.xml.rpc.ServiceException;

    public webService.soap.SOAPBookService getSOAPBookService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
