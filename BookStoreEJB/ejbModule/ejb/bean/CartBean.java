package ejb.bean;

import java.util.ArrayList;
import java.util.List;

import ejb.remote.CartManager;
import entityBean.Book;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class CartBean
 */

@Stateful
public class CartBean implements CartManager {

    /**
     * Default constructor. 
     */
    public CartBean() {
        // TODO Auto-generated constructor stub
    }
    
    List<Book> contents;
    String customerId;
    String customerName;

    @Override
    public void initialize(String person) {
        if (person != null) {
            customerName = person;
        }

        customerId = "0";
        contents = new ArrayList<>();
    }

    @Override
    public void initialize(String person, String id) {
        if (person != null) {
            customerName = person;
        }

        customerId = id;

        contents = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        contents.add(book);
    }

    @Override
    public void removeBook(Book book) {
        boolean result = contents.remove(book);
    }

    @Override
    public List<Book> getContents() {
        return contents;
    }

    @Remove()
    @Override
    public void remove() {
        contents = null;
    }
}
