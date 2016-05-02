package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.*;

@Remote
public interface OrderManager {
	public void addOrder(Order order);
	public List<Order> getOrderByBuyer(User user);
	public List<Order> getOrderByBook(Book book);
	public List<Order> getOrderByTime(String time, int flag);
	public List<Order> getOrderByUserAndTime (User user, String time, int flag);
}
