package dao.ejb.remote;

import java.util.List;
import javax.ejb.Remote;

import entityBean.Book;

@Remote
public interface BookDaoRemote {
	public void addBookInfo(Book book);

	public List<Book> getBookByTitle(String title);
	
	public List<Book> getBookByAuthor(String author);
	
	public List<Book> listBooksByFuzzyTitle(String title);
	
	public void deleteBook(Book book);
	
	public Book updateBookInfo(Book book);
}