package ejb.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import ejb.remote.CartManager;
import entityBean.Book;
import entityBean.Order;
import entityBean.User;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.*;
import javax.jms.*;

/**
 * Session Bean implementation class CartBean
 */

@Stateful
public class CartBean implements CartManager {

    /**
     * Default constructor. 
     */
    public CartBean() {
        // TODO Auto-generated constructor stub
    }
    
    List<Book> contents;
    List<Integer> cnt;
    
    User user;

    @Override
    public void initialize(User u) {
        user = u;
        contents = new ArrayList<Book>();
        cnt = new ArrayList<Integer>();
    }

    @Override
    public void addBook(Book book) {
    	if (contents == null) {
    		System.out.println("Without initialization");
    		return;
    	}
    	else {
    		System.out.println("Initialized");
    		System.out.println(contents.size());
    	}
    	for (int i = 0; i < contents.size(); i++) {
    		System.out.println("i: "+i);
    		if (contents.get(i).getBookId() == book.getBookId()) {
    			cnt.set(i, cnt.get(i)+1);
    			return;
    		}
    	}
        contents.add(book);
        cnt.add(1);
    }

    @Override
    public void removeBook(Book book) {
    	for (int i = 0; i < contents.size(); i++) {
    		if (contents.get(i).getBookId() == book.getBookId()) {
    			contents.remove(i);
    			cnt.remove(i);
    			break;
    		}
    	}
    }

    @Override
    public List<Book> getContents() {
        return contents;
    }
    
    @Override
    public List<Integer> getCnt() {
        return cnt;
    }

    @Remove()
    @Override
    public void clear() {
        contents = null;
        cnt = null;
    }

    static int commitCnt = 0;
    
	@Override
	public void commitToOrder() {
		try {
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			QueueConnectionFactory factory
			  = (QueueConnectionFactory)context.lookup("ConnectionFactory");
			Queue dest = (Queue)context.lookup("jms/orderMessageQueue");
			QueueConnection cnn = factory.createQueueConnection();
			QueueSession session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(dest);

			for (int i = 0; i < contents.size(); i++) {
				Order order = new Order();
				order.setBookTitle(contents.get(i).getTitle());
				order.setBuyer(user.getUsername());
				order.setPrice(contents.get(i).getPrice());
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int mouth = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DATE);
				System.out.println(year + " " + mouth + " " + day);
				String orderDate = String.valueOf(year) + "-" + String.valueOf(mouth) + "-" + String.valueOf(day);
				order.setOrderTime(orderDate);
				
				String idStr = String.valueOf(year) + String.valueOf(mouth) + String.valueOf(day) 
								+ String.valueOf(c.get(Calendar.HOUR_OF_DAY)) + String.valueOf(c.get(Calendar.MINUTE))
								+ String.valueOf(c.get(Calendar.SECOND)) + String.valueOf(user.getUserId());
				order.setOrderId(Integer.parseInt(idStr));
				
				ObjectMessage om = session.createObjectMessage(order);
				sender.send(om);
			}
			
//            Order order = new Order();
//            order.setOrderId(1);
//            order.setBookTitle("book");
//            order.setBuyer("luo");
//            order.setPrice(11.5);
//            order.setOrderTime("2015-05-01");
//            ObjectMessage om = session.createObjectMessage(order);
//            System.out.println("before send " + commitCnt);
//            commitCnt++;
//            sender.send(om);
//            //producer.send(om);
            cnn.close();
            session.close();
            
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
    
    
}
