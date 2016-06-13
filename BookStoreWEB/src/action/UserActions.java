package action;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.UserBean;
import ejb.remote.UserManager;
import entityBean.User;

public class UserActions extends ActionSupport{
	private User user;
	private String actions;
	private String ja;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	public void login (UserManager um) {
		if (actions.equals("login")) {
			user = um.login(user);
		}
		else if (actions.equals("register")) {
			user = um.register(user);
		}
		
		if (user == null) {
    		System.out.println("fail");
    		JSONObject jo = new JSONObject();
    		try {
				jo.append("msg", "fail");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		ja = jo.toString();
    		System.out.println(ja);
    	}
    	else {
    		System.out.println("success");
    		JSONObject jo = new JSONObject();
    		try {
				jo.append("msg", "success");
				jo.append("role", user.getAdm());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		ja = jo.toString();
    		System.out.println(ja);
    		HttpSession session = ServletActionContext.getRequest().getSession();
    		session.setAttribute("user", user);
    	}
		return;
	}
	
	public String execute() throws Exception{
		System.out.println("In action");
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        
	    final String moduleName = "BookStoreEJB";        
	    final String distinctName = "";                  
	    final String beanName = UserBean.class.getSimpleName();           
	    final String viewClassName = UserManager.class.getName();        
	    try {
	    	UserManager um = (UserManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    	if (actions.equals("login") || actions.equals("register")) {
			    login (um);
		    }
	    } catch (NamingException e) {
	    	 // TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    	
		return "success";
	}
}
