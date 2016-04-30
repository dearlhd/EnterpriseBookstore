package ejb.bean;

import java.util.ArrayList;
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
    	for (int i = 0; i < contents.size(); i++) {
    		if (contents.get(i).getBookId() == book.getBookId()) {
    			cnt.set(i, cnt.get(i)+1);
    			return;
    		}
    	}
        contents.add(book);
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

	@Override
	public void commitToOrder() {
//		QueueConnection conn = null;  
//        QueueSession session = null;
//		try {
//			Properties props = new Properties();  
//            Properties properties = new Properties();  
//            properties.put("java.naming.factory.initial",   //提供JNDI 服务器  
//                             "org.jnp.interfaces.NamingContextFactory");  
//            properties.put("java.naming.factory.url.pkgs",  
//                             "org.jboss.naming:org.jnp.interfaces");  
//            properties.put("java.naming.provider.url", "localhost:1099");
//            
//            InitialContext ctx = new InitialContext(props);  
//            QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");  
//            conn = factory.createQueueConnection();
//            session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);  
//            Destination destination = (Queue) ctx.lookup("queue/orderMessageQueue");  
//            MessageProducer producer = session.createProducer(destination);
//            Order order = new Order();
//            order.setBookTitle("book");
//            order.setBuyer("luo");
//            order.setPrice(11.5);
//            order.setOrderTime("2015-05-01");
//            ObjectMessage om = session.createObjectMessage(order);
//            producer.send(om);
//            
//            
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
	}
    
    
}
