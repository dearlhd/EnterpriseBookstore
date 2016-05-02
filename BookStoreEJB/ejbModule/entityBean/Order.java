package entityBean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long orderId;
	
	private String bookTitle;
	private String buyer;
	private double price;
	private String orderTime;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
}
