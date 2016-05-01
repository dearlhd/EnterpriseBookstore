package ejb.bean;

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

}
