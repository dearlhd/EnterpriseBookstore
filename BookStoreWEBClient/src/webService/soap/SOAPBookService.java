/**
 * SOAPBookService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService.soap;

public interface SOAPBookService extends java.rmi.Remote {
    public entityBean.Book getBook(java.lang.String title) throws java.rmi.RemoteException;
}
