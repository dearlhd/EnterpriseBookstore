package action;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.OrderBean;
import ejb.remote.OrderManager;
import entityBean.Order;

public class OrderActions extends ActionSupport{
	private Order order;
	private String actions;
	private String ja;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	
	public String excute() throws Exception {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //������.EAR�������ƣ����������JAR�����Ļ�������������
	    final String moduleName = "BookStoreEJB";        //�������㷢����JAR�ļ�������helloworld.jar,������Ӧ��Ϊhelloworld��ȥ����׺����
	    final String distinctName = "";                  //���û�ж��������ϸ�����ƣ�����������
	    final String beanName = OrderBean.class.getSimpleName();           //����Ϊʵ���������
	    final String viewClassName = OrderManager.class.getName();        //����Ϊ��Ľӿ�����
		try {
			OrderManager om = (OrderManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	    
		return "success";
	}
}
