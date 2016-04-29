package ejb.bean;

import java.util.List;

import ejb.remote.BookDaoRemote;
import entityBean.Book;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class BookDao
 */
@Stateless
@LocalBean
public class BookDao implements BookDaoRemote {

    /**
     * Default constructor. 
     */
    public BookDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void saveBookInfo(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksByFuzzyTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookInfo(Book book) {
		// TODO Auto-generated method stub
		
	}

}
