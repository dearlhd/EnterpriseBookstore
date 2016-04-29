package ejb.bean;

import java.util.List;

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
    	return "hello "+str;
    }
    
    @Override
    public User retUser(int n) {
    	User user= new User();
    	user.setUsername("luo");
    	user.setPassword("123");
    	user.setAge(22);
    	user.setEmail("abc@123.com");
    	user.setUserId(1);
    	System.out.println("Before dao");
    	List<User> ulist = dao.getUsersByFuzzyName("l");
    	System.out.println(ulist.size());
    	if (ulist.size() > 0) {
    		System.out.println(ulist.get(0).getUsername());
    	}
    	user = dao.getUserByID(1);
    	return user;
    }
}
