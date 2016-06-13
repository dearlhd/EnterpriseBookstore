package action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.BookBean;
import ejb.remote.BookManager;
import entityBean.Book;

public class BookActions extends ActionSupport{
	private static final long serialVersionUID = -2544932860809760770L;
	
	private Book book;
	private String actions;
	private String ja;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public String getJa() {
		return ja;
	}
	public void setJa(String ja) {
		this.ja = ja;
	}
	
	public void queryBook(BookManager bm) {
		System.out.println("queryBook");
		List<Book> books = new ArrayList<Book>();
		if (actions.equals("queryByTitle")) {
			System.out.println("by title");
			//books = bm.searchBooksByTitle(book.getTitle());
			books = bm.searchBooksByFuzzyTitle(book.getTitle());
			System.out.println("title: "+books.get(0).getTitle() + " price: "+books.get(0).getPrice());
		}
		else if (actions.equals("queryByAuthor")) {
			System.out.println("by author");
			System.out.println("Author: " + book.getAuthor());
			books = bm.searchBooksByAuthor(book.getAuthor());
		}
		
		for (int i = 0; i < books.size(); i++) {
			ja = JSONArray.fromObject(books).toString();
		}
		System.out.println("Books: "+ja);
	}
	
	void addBook(BookManager bm) {
		bm.addBook(book);
	}
	
	public String execute() throws Exception{
		System.out.println("In book action");
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
	    try {
	    	BookManager bm = (BookManager) context.lookup("ejb:/BookStoreEJB//BookBean!ejb.remote.BookManager");
	    	if (actions.equals("queryByTitle") || actions.equals("queryByAuthor")) {
	    		queryBook(bm);
		    }
	    	else if (actions.equals("addBook")) {
	    		addBook(bm);
	    	}
	    } catch (NamingException e) {
	    	 // TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    	
		return "success";
	}
}
