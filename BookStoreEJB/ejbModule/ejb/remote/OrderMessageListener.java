package ejb.remote;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;

import ejb.remote.OrderManager;
import ejb.remote.BookManager;
import entityBean.Book;
import entityBean.Order;

/**
 * Message-Driven Bean implementation class for: OrderMessageListener
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/orderMessageQueue")
				
		})
public class OrderMessageListener implements MessageListener {

	@EJB
	OrderManager orderManager;
	
	@EJB
	BookManager bookManager;
    /**
     * Default constructor. 
     */
    public OrderMessageListener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
        	if (message instanceof ObjectMessage) {
        		ObjectMessage om = (ObjectMessage)message;
        		Order order = (Order)om.getObject();
        		System.out.println(order.getBookTitle());
        		orderManager.addOrder(order);
        		
        		Book book = new Book();
        		book.setBookId(order.getBookId());
        		try {
        			System.out.println("Before substract book, order cnt is "+order.getCount());
					bookManager.subtractBookCount(book, order.getCount());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        } catch (JMSException e) {
        	e.printStackTrace();
        }
    }

}
