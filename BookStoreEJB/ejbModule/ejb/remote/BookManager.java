package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Book;

@Remote
public interface BookManager {
	public void addBook(Book book);
	public void deleteBook(Book book);
	public Book updateBookInfo(Book book);
	
	public Book getBookById (long bookId);
	
	public List<Book> searchBooksByTitle(String title);
	public List<Book> searchBooksByAuthor(String author);
	
	public List<Book> searchBooksByFuzzyTitle(String title);
}
