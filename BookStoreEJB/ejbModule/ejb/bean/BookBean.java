package ejb.bean;

import java.util.Calendar;
import java.util.List;

import dao.ejb.remote.BookDaoRemote;
import ejb.remote.BookManager;
import entityBean.Book;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class BookBean
 */
@Stateless
@LocalBean
public class BookBean implements BookManager {
	@EJB
	BookDaoRemote dao;
    /**
     * Default constructor. 
     */
    public BookBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addBook(Book book) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int mouth = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		
		String bidStr =  String.valueOf(c.get(Calendar.HOUR_OF_DAY)) + String.valueOf(c.get(Calendar.MINUTE))
						+ String.valueOf(c.get(Calendar.SECOND));
		
		book.setBookId(Long.parseLong(bidStr));
		
		dao.addBookInfo(book);
		return;
	}

	@Override
	public void deleteBook(Book book) {
		dao.deleteBook(book);
	}

	@Override
	public Book updateBookInfo(Book book) {
		return dao.updateBookInfo(book);
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		System.out.println("Search by title");
		return dao.getBookByTitle(title);
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		System.out.println("Search by author");
		return dao.getBookByAuthor(author);
	}

	@Override
	public List<Book> searchBooksByFuzzyTitle(String title) {
		System.out.println("Search by fuzzy title");
		return dao.listBooksByFuzzyTitle(title);
	}

	@Override
	public Book getBookById(long bookId) {
		System.out.println("Search by id");
		return dao.getBookById(bookId);
	}

	@Override
	public void subtractBookCount(Book book, int count) throws Exception {
		dao.subtractBook(book, count);
		return;
	}
}
