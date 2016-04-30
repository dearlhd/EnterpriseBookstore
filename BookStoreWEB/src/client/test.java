package client;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.bean.BookBean;
import ejb.bean.UserBean;
import ejb.remote.UserManager;
import entityBean.User;

public class test {
	public static void main(String [] args) throws Exception{
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		 jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		 final Context context = new InitialContext(jndiProperties);

		 final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������

	     final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����

	     final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������

	     final String beanName = UserBean.class.getSimpleName();           //����Ϊʵ���������

	     final String viewClassName = UserManager.class.getName();        //����Ϊ��Ľӿ�����
	     try {
	    	 System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    	 UserManager f = (UserManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
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