package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Book;
import entityBean.User;

@Remote
public interface CartManager {
	public void initialize(User user);

    public void addBook(Book book);

    public void removeBook(Book book);

    public List<Book> getContents();
    
    public List<Integer> getCnt();

    public void clear();
    
    public void commitToOrder();
}
