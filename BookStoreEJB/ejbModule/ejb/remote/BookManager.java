package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Book;

@Remote
public interface BookManager {
	public void addBook(Book book);
	public void deleteBook(Book book);
	public Book updateBookInfo(Book book);
	public List<Book> searchBooksByTitle(String title);
	public List<Book> searchBooksByAuthor(String author);
}
