package ejb.remote;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

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
        	}
        } catch (JMSException e) {
        	e.printStackTrace();
        }
    }

}
