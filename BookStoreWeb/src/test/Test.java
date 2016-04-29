package test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.bean.UserBean;
import ejb.remote.UserRemote;
import entityBean.User;


public class Test {
	public static void main(String [] args) throws Exception{
		 final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		 jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		 final Context context = new InitialContext(jndiProperties);

		 final String appName = "";        //这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空

	     final String moduleName = "BookStoreEJB";        //这里是你发布的JAR文件名，如helloworld.jar,则这里应该为helloworld。去掉后缀即可

	     final String distinctName = "";                  //如果没有定义其更详细的名称，则这里留空

	     final String beanName = UserBean.class.getSimpleName();           //这里为实现类的名称

	     final String viewClassName = UserRemote.class.getName();        //这里为你的接口名称
	     try {
	    	 UserRemote f = (UserRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    	 User user = f.retUser(1);
	    	 if (user == null) {
	    		 System.out.println("null");
	    	 }
	    	 else {
		    	 System.out.println(user.getUsername());
		    	 System.out.println(user.getEmail());
		    	 System.out.println(f.sayHello("dong"));
	    	 }
	     } catch (NamingException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	    }
	 }
}
