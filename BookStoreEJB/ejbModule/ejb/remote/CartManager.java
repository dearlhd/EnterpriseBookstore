package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Book;

@Remote
public interface CartManager {
	public void initialize(String person);

    public void initialize(String person, String id);

    public void addBook(Book book);

    public void removeBook(Book book);

    public List<Book> getContents();

    public void remove();
}
