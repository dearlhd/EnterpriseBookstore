package ejb.bean;

import java.util.List;

import ejb.remote.OrderRemote;
import entityBean.Book;
import entityBean.Order;
import entityBean.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrderBean
 */
@Stateless
@LocalBean
public class OrderBean implements OrderRemote {

    /**
     * Default constructor. 
     */
    public OrderBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> checkOrderByBuyer(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> checkOrderByBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> checkOrderByTime(String time, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

}
