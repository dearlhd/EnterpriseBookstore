package action;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.CartBean;
import ejb.remote.CartManager;
import entityBean.Book;
import entityBean.User;

public class CartActions extends ActionSupport{
	private Book book;
	private String actions;
	private String ja;
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
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	void initCartManager () throws NamingException {
		User user = new User ();
		user.setAge(1);
		user.setUsername("luo");
		
		System.out.println("Initializing");
		
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空
	    final String moduleName = "BookStoreEJB";        //这里是你发布的JAR文件名，如helloworld.jar,则这里应该为helloworld。去掉后缀即可
	    final String distinctName = "";                  //如果没有定义其更详细的名称，则这里留空
	    final String beanName = CartBean.class.getSimpleName();           //这里为实现类的名称
	    final String viewClassName = CartManager.class.getName();        //这里为你的接口名称
	    CartManager cartManager = (CartManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
	    cartManager.initialize(user);
	    HttpSession session = ServletActionContext.getRequest().getSession();
	    session.setAttribute("cartManager", cartManager);
	}
	
	public String execute() throws Exception{
		System.out.println("In cart actions");
		book.setBookId(1);
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			CartManager cm = (CartManager)session.getAttribute("cartManager");
			
			if (cm == null) {
				initCartManager();
				cm = (CartManager)session.getAttribute("cartManager");
			}
			
			if (actions.equals("addBook")) {
				System.out.println("Before add");
				
				cm.addBook(book);
				List<Book> lb = cm.getContents();
				List<Integer> li = cm.getCnt();
				System.out.println("Size: "+lb.size());
				System.out.println(li.get(0));
				session.setAttribute("cartManager", cm);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
}
