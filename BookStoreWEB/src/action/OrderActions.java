package action;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import utils.redis.RedisClient;

import com.opensymphony.xwork2.ActionSupport;

import ejb.bean.OrderBean;
import ejb.remote.OrderManager;
import entityBean.Order;
import entityBean.User;

public class OrderActions extends ActionSupport{
	private Order order;
	private String actions;
	private String queryCondition;
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
	
	public String getQueryCondition() {
		return queryCondition;
	}
	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}
	void queryOrder (OrderManager om) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		
		RedisClient rc = new RedisClient();
		
		if (actions.equals("queryAllOrder")) {
			List<Order> lo = rc.getOrders("order:"+user.getUsername());
			if (lo == null) {
				lo = om.getOrderByBuyer(user);
				rc.setOrders(lo);
			}
			else {
				System.out.println("In redis");
				System.out.println("size: "+lo.size());
			}
			
			System.out.println("Query All: ");
			
			JSONArray jarray = JSONArray.fromObject(lo);
			ja = jarray.toString();
		}
		else if (actions.equals("queryByYear")){
			List<Order> lo = om.getOrderByUserAndTime(user, queryCondition, 1);
			JSONArray jarray = JSONArray.fromObject(lo);
			ja = jarray.toString();
		}
		else if (actions.equals("queryByMonth")){
			List<Order> lo = om.getOrderByUserAndTime(user, queryCondition, 2);
			JSONArray jarray = JSONArray.fromObject(lo);
			ja = jarray.toString();
		}
		else if (actions.equals("queryByDay")){
			List<Order> lo = om.getOrderByUserAndTime(user, queryCondition, 3);
			JSONArray jarray = JSONArray.fromObject(lo);
			ja = jarray.toString();
		}
	}
	
	public String execute() throws Exception {
		System.out.println("In Order Actions: "+actions);
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";        //这里是.EAR包的名称，如果你打包成JAR发布的话，这里则留空
	    final String moduleName = "BookStoreEJB";        //这里是你发布的JAR文件名，如helloworld.jar,则这里应该为helloworld。去掉后缀即可
	    final String distinctName = "";                  //如果没有定义其更详细的名称，则这里留空
	    final String beanName = OrderBean.class.getSimpleName();           //这里为实现类的名称
	    final String viewClassName = OrderManager.class.getName();        //这里为你的接口名称
		try {
			OrderManager om = (OrderManager) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
			
			if (actions.equals("queryAllOrder") || actions.equals("queryByYear") || actions.equals("queryAllMonth") || actions.equals("queryAllDay")) {
				queryOrder (om);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	    
		return "success";
	}
}
