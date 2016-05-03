package dao.ejb.bean;

import java.util.ArrayList;
import java.util.List;

import dao.ejb.remote.BookDaoRemote;
import entityBean.Book;
import entityBean.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class BookDao
 */
@Stateless
@LocalBean
public class BookDao implements BookDaoRemote {
	@PersistenceContext(unitName="BookStoreEJB")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public BookDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addBookInfo(Book book) {
		em.persist(book);
	}

	List<Book> getBooksBySql (Query query) {
		List booklist = query.getResultList();
		if (booklist.size() == 0) {
			return null;
		}
		else {
			List<Book> books = new ArrayList<Book>();
			for (int i = 0; i < booklist.size(); i++) {
				Object []obj = (Object[]) booklist.get(i);
				Book book = new Book();
				book.setBookId(Long.parseLong(obj[0].toString()));
				book.setTitle(obj[1].toString());
				book.setAuthor(obj[2].toString());
				book.setPrice(Double.parseDouble(obj[3].toString()));
				book.setCount(Integer.parseInt(obj[4].toString()));
				
				books.add(book);
			}
			return books;
		}
	}
	

	@Override
	public Book getBookById(long bookId) {
		System.out.println("Dao: get by id: " + bookId);
		Query query = em.createNativeQuery("select * from Books Where bookId=:bookId");
		query.setParameter("bookId", bookId);
		List<Book> blist = getBooksBySql(query);
		if (blist.size() == 0) {
			return null;
		}
		return blist.get(0);
	}
	
	@Override
	public List<Book> getBookByTitle(String title) {
		Query query = em.createNativeQuery("select * from Books Where title=:title");
		query.setParameter("title", title);
		return getBooksBySql(query);
	}

	@Override
	public List<Book> getBookByAuthor(String author) {
		Query query = em.createNativeQuery("select * from Books Where author=:author");
		query.setParameter("author", author);
		return getBooksBySql(query);
	}
	
	@Override
	public List<Book> listBooksByFuzzyTitle(String title) {
		Query query = em.createNativeQuery("select * from Books Where title like :title");
		query.setParameter("title", "%"+title+"%");
		return getBooksBySql(query);
	}

	@Override
	public void deleteBook(Book book) {
		em.remove(book);
		em.flush();
	}

	@Override
	public Book updateBookInfo(Book book) {
		Book newbook = em.merge(book);
		em.flush();
		return newbook;
	}
	
	@Override
	public void subtractBook(Book book, int cnt) throws Exception {
		System.out.println("In bookDao: subtractBook");
		book = getBookById(book.getBookId());
		System.out.println("book cnt in db: "+book.getCount());
		System.out.println("book cnt to sub" + cnt);
		if (book.getCount() < cnt) {
			throw new Exception("Count of Book is not enough!");
		}
		else {
			int newCnt = book.getCount() - cnt;
			book.setCount(newCnt);
			System.out.println("to merge! title: "+book.getTitle() + " cnt: "+book.getCount() + " author: "+book.getAuthor());
			em.merge(book);
			em.flush();
		}
	}

}
