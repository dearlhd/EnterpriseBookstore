package dao.ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.*;

@Remote
public interface OrderDaoRemote {
	public void addOrder(Order order);
	public List<Order> checkOrderByBuyer(User user);
	public List<Order> checkOrderByBook (Book book);
	public List<Order> checkOrderByTime (String time, int flag);
	
	public List<Order> checkOrderByUserAndTime(String username, String time, int flag);
}
