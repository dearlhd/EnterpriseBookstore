package ejb.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import ejb.remote.CartManager;
import entityBean.Book;
import entityBean.Order;
import entityBean.User;

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
    	System.out.println("Initializing cart info in cartManager");
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
    	for (int i = 0; i < contents.size(); i++) {
    		System.out.println("i: "+i);
    		if (contents.get(i).getBookId() == book.getBookId()) {
    			cnt.set(i, cnt.get(i)+1);
    			return;
    		}
    	}
        contents.add(book);
        cnt.add(1);
        System.out.println("In cartManager! function addBook: contents.size="+contents.size());
    }

    @Override
    public boolean removeBook(Book book) {
    	System.out.println("In cartManager! removeBook: to remove bookId="+book.getBookId());
    	for (int i = 0; i < contents.size(); i++) {
    		System.out.println("In cartManager! removeBook: for bookId="+book.getBookId());
    		if (contents.get(i).getBookId() == book.getBookId()) {
    			System.out.println("In cartManager! removeBook: book was found!");
    			contents.remove(i);
    			cnt.remove(i);
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public List<Book> getContents() {
        return contents;
    }
    
    @Override
    public List<Integer> getCnt() {
        return cnt;
    }

    
    @Override
    public void clear() {
        contents = null;
        cnt = null;
        contents = new ArrayList<Book>();
        cnt = new ArrayList<Integer>();
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
				int mouth = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DATE);
				System.out.println(year + " " + mouth + " " + day);
				String orderDate = String.valueOf(year) + "-" + String.valueOf(mouth) + "-" + String.valueOf(day);
				order.setOrderTime(orderDate);
				order.setCount(cnt.get(i));
				order.setBookId(contents.get(i).getBookId());
				
				String idStr =  String.valueOf(c.get(Calendar.HOUR_OF_DAY)) + String.valueOf(c.get(Calendar.MINUTE))
								+ String.valueOf(c.get(Calendar.SECOND)) + String.valueOf(user.getUserId()) + String.valueOf(i);
				order.setOrderId(Long.parseLong(idStr));
				
				ObjectMessage om = session.createObjectMessage(order);
				sender.send(om);
				
			}
			clear();
            cnn.close();
            session.close();
            
            
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
    
    
}
