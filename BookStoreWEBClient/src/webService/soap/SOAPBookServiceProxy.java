package webService.soap;

public class SOAPBookServiceProxy implements webService.soap.SOAPBookService {
  private String _endpoint = null;
  private webService.soap.SOAPBookService sOAPBookService = null;
  
  public SOAPBookServiceProxy() {
    _initSOAPBookServiceProxy();
  }
  
  public SOAPBookServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPBookServiceProxy();
  }
  
  private void _initSOAPBookServiceProxy() {
    try {
      sOAPBookService = (new webService.soap.SOAPBookServiceServiceLocator()).getSOAPBookService();
      if (sOAPBookService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPBookService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPBookService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPBookService != null)
      ((javax.xml.rpc.Stub)sOAPBookService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.soap.SOAPBookService getSOAPBookService() {
    if (sOAPBookService == null)
      _initSOAPBookServiceProxy();
    return sOAPBookService;
  }
  
  public entityBean.Book getBook(java.lang.String title) throws java.rmi.RemoteException{
    if (sOAPBookService == null)
      _initSOAPBookServiceProxy();
    return sOAPBookService.getBook(title);
  }
  
  
}