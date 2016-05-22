package webService.soap;

import java.util.Hashtable;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.remote.BookManager;
import entityBean.Book;

@WebService
public class SOAPBookService {
	private BookManager bm;
	
	public SOAPBookService () {
		initUserManager();
	}
	
	private void initUserManager () {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			final Context context = new InitialContext(jndiProperties);    
		    bm = (BookManager) context.lookup("ejb:/BookStoreEJB//BookBean!ejb.remote.BookManager");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Book getBook(@WebParam(name = "title") String title) {
		Book book = bm.searchBooksByFuzzyTitle(title).get(0);
		return book;
	}
}
