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
	}
	
	public String execute() throws Exception{
		System.out.println("In action");
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空
	    final String moduleName = "BookStoreEJB";        //这里是你发布的JAR文件名，如helloworld.jar,则这里应该为helloworld。去掉后缀即可
	    final String distinctName = "";                  //如果没有定义其更详细的名称，则这里留空
	    final String beanName = UserBean.class.getSimpleName();           //这里为实现类的名称
	    final String viewClassName = UserManager.class.getName();        //这里为你的接口名称
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
