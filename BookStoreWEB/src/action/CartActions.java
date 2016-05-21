package action;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import redis.RedisClient;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.BookBean;
import ejb.bean.CartBean;
import ejb.remote.BookManager;
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
		System.out.println("Initializing cart manager");
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		
		System.out.println("Initializing cart manager");
		
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������
	    final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����
	    final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������
	    final String beanName = CartBean.class.getSimpleName();           //����Ϊʵ���������
	    final String viewClassName = CartManager.class.getName();        //����Ϊ��Ľӿ�����
	    CartManager cartManager = (CartManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
	    cartManager.initialize(user);
	    
	    session.setAttribute("cartManager", cartManager);
	}
	
	void addBook (CartManager cm) throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		BookManager bm = (BookManager) context.lookup("ejb:/BookStoreEJB//" + BookBean.class.getSimpleName() + "!" + BookManager.class.getName());
		
		book = bm.getBookById(book.getBookId());
		
		cm.addBook(book);
		getCart(cm);
	}
	
	void removeBook (CartManager cm) throws NamingException {
		cm.removeBook(book);

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("cartManager", cm);
	}
	
	void getCart(CartManager cm) {
		System.out.println("In function: getCart");
		List<Book> lb = cm.getContents();
		List<Integer> li = cm.getCnt();
		
		System.out.println("lb size: " + lb.size() + "     li size: " + li.size());
		
		JSONArray jarray = new JSONArray();
		for (int i = 0; i < lb.size(); i++) {
			JSONObject jo1 = JSONObject.fromObject(lb.get(i));
			jo1.put("countInCart", li.get(i));
			jarray.add(jo1);
		}
		
		ja = jarray.toString();
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("cartManager", cm);
	}
	
	void clearCart(CartManager cm) {
		cm.clear();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("cartManager", cm);
	}
	
	void commitToOrder(CartManager cm) {
		cm.commitToOrder();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("cartManager", cm);
		
		RedisClient rc = new RedisClient();
		User user = (User)session.getAttribute("user");
		rc.delOrders("order:"+user.getUsername());
	}
	
	public String execute() throws Exception{
		System.out.println("In cart actions: "+actions);
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			CartManager cm = (CartManager)session.getAttribute("cartManager");
			
			if (cm == null) {
				initCartManager();
				cm = (CartManager)session.getAttribute("cartManager");
			}
			
			if (actions.equals("addBook")) {
				addBook(cm);
			}
			else if (actions.equals("getCart")){
				getCart(cm);
			}
			else if (actions.equals("removeBook")) {
				removeBook(cm);
			}
			else if (actions.equals("clearCart")) {
				clearCart(cm);
			}
			else if (actions.equals("commitToOrder")) {
				commitToOrder(cm);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
}
