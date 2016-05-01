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
			books = bm.searchBooksByTitle(book.getTitle());
			books = bm.searchBooksByFuzzyTitle(book.getTitle());
			System.out.println(books.size());
		}
		else if (actions.equals("queryByAuthor")) {
			System.out.println("by author");
			books = bm.searchBooksByAuthor(book.getAuthor());
		}
		else {
			System.out.println("actions: "+actions);
		}
		
		for (int i = 0; i < books.size(); i++) {
			ja = JSONArray.fromObject(books).toString();
		}
		System.out.println("Books: "+ja);
	}
	
	public String execute() throws Exception{
		System.out.println("In book action");
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空
	    final String moduleName = "BookStoreEJB";        //这里是你发布的JAR文件名，如helloworld.jar,则这里应该为helloworld。去掉后缀即可
	    final String distinctName = "";                  //如果没有定义其更详细的名称，则这里留空
	    final String beanName = BookBean.class.getSimpleName();           //这里为实现类的名称
	    final String viewClassName = BookManager.class.getName();        //这里为你的接口名称
	    try {
	    	BookManager bm = (BookManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    	if (actions.equals("queryByTitle") || actions.equals("queryByAuthor")) {
	    		queryBook(bm);
		    }
	    } catch (NamingException e) {
	    	 // TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    	
		return "success";
	}
}
