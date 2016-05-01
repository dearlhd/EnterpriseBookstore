package ejb.bean;

import java.util.List;

import dao.ejb.remote.OrderDaoRemote;
import ejb.remote.OrderManager;
import entityBean.Book;
import entityBean.Order;
import entityBean.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrderBean
 */
@Stateless
@LocalBean
public class OrderBean implements OrderManager {
	@EJB
	OrderDaoRemote dao;
    /**
     * Default constructor. 
     */
    public OrderBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addOrder(Order order) {
		System.out.println("In order bean");
		dao.addOrder(order);
	}

	@Override
	public List<Order> getOrderByBuyer(User user) {
		return dao.checkOrderByBuyer(user);
	}

	@Override
	public List<Order> getOrderByBook(Book book) {
		return dao.checkOrderByBook(book);
	}

	@Override
	public List<Order> getOrderByTime(String time, int flag) {
		// TODO Auto-generated method stub
		return dao.checkOrderByTime(time, flag);
	}

}
