package dao.ejb.bean;

import java.util.ArrayList;
import java.util.List;

import dao.ejb.remote.OrderDaoRemote;
import entityBean.Book;
import entityBean.Order;
import entityBean.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class OrderBean
 */
@Stateless
@LocalBean
public class OrderDao implements OrderDaoRemote {
	@PersistenceContext(unitName="BookStoreEJB")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public OrderDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addOrder(Order order) {
		em.persist(order);;
		return;
	}

	List<Order> checkOrderBySql(Query query){
		List orderlist = query.getResultList();
		if (orderlist.size() == 0) {
			return null;
		}
		else {
			List<Order> orders = new ArrayList<Order>();
			for (int i = 0; i < orderlist.size(); i++) {
				Object []obj = (Object[]) orderlist.get(i);
				Order order = new Order();
				order.setOrderId(Integer.parseInt(obj[0].toString()));
				order.setBookTitle(obj[1].toString());
				order.setBuyer(obj[2].toString());
				order.setPrice(Double.parseDouble(obj[3].toString()));
				order.setOrderTime(obj[4].toString());
				
				orders.add(order);
			}
			return orders;
		}
	}
	
	@Override
	public List<Order> checkOrderByBuyer(User user) {
		Query query = em.createNativeQuery("select * from Orders Where buyer=:buyer");
		query.setParameter("buyer", user.getUsername());
		return checkOrderBySql(query);
	}

	@Override
	public List<Order> checkOrderByBook(Book book) {
		Query query = em.createNativeQuery("select * from Orders Where bookTitle=:bookTitle");
		query.setParameter("bookTitle", book.getTitle());
		return checkOrderBySql(query);
	}

	@Override
	public List<Order> checkOrderByTime(String time, int flag) {
		//°´Äê      by year
		if (flag == 1) {
			Query query = em.createNativeQuery("select * from Orders Where orderTime like :time");
			query.setParameter("time", "%"+time+"-%");
			return checkOrderBySql(query);
		}
		else if (flag == 2) {
			Query query = em.createNativeQuery("select * from Orders Where orderTime like :time");
			query.setParameter("time", "%-"+time+"-%");
			return checkOrderBySql(query);
		}
		else if (flag == 3) {
			Query query = em.createNativeQuery("select * from Orders Where orderTime like :time");
			query.setParameter("time", "%-"+time+"%");
			return checkOrderBySql(query);
		}
		return null;
	}

}
