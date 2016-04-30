package ejb.bean;

import java.util.List;

import dao.ejb.remote.UserDaoRemote;
import ejb.remote.UserManager;
import entityBean.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserManager {

	@EJB
	private UserDaoRemote dao;
    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
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

	@Override
	public User login(User user) {
		String name = user.getUsername();
		String psw = user.getPassword();
		
		System.out.println("In login bean:");
		User userInDB = dao.getUserByName(name);
		if (userInDB != null && userInDB.getPassword().equals(psw)) {
			return userInDB;
		}
		
		return null;
	}

	@Override
	public User register(User user) {
		String name = user.getUsername();
		
		User userInDB = dao.getUserByName(name);
		if (userInDB == null) {
			dao.addUser(user);
			return user;
		}
		
		return null;
	}

	@Override
	public User searchUserById(int n) {
		return dao.getUserByID(n);
	}

	@Override
	public User searchUserByName(String name) {
		return dao.getUserByName(name);
	}

	@Override
	public List<User> searchUsersByFuzzyName(String name) {
		return dao.getUsersByFuzzyName(name);
	}

	@Override
	public User updateUserInfo(User user) {
		return dao.updateUserInfo(user);
	}
}
