package ejb.bean;

import ejb.remote.UserDaoRemote;
import ejb.remote.UserRemote;
import entityBean.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserRemote {

	@EJB
	private UserDaoRemote dao;
    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean login(String username, String password) {

		return false;
    }
    
    @Override
    public String sayHello(String str) {
    	System.out.println("hello world");
    	return "hi "+str;
    }
    
    @Override
    public User retUser(int n) {
    	System.out.println("Before new");
    	System.out.println("After new");
    	User user= new User();
    	user.setUsername("qqq");
    	user.setPassword("123123");
    	dao.addUser(user);
    	return user;
    }
}
