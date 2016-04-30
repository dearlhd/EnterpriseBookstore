package action;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.UserBean;
import ejb.remote.UserManager;
import entityBean.User;

public class UserActions extends ActionSupport{
	private User user;
	private String action;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String execute() throws Exception{
		System.out.println("In action");
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		 jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		 final Context context = new InitialContext(jndiProperties);
		 final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������
	     final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����
	     final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������
	     final String beanName = UserBean.class.getSimpleName();           //����Ϊʵ���������
	     final String viewClassName = UserManager.class.getName();        //����Ϊ��Ľӿ�����
	     try {
	    	 UserManager um = (UserManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    	 System.out.println("Before login");
	    	 user = um.login(user);
	    	 System.out.println("after login");
	    	 if (user == null) {
	    		 System.out.println("null");
	    	 }
	    	 else {
		    	 System.out.println(user.getUsername());
		    	 System.out.println(user.getEmail());
	    	 }
	     } catch (NamingException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	    }
		return "success";
	}
}
