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
		System.out.println("register: "+userInDB.getUsername());
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
	
	@Override
	public User deleteUser (String name) {
		if (dao.getUserByName(name) != null) {
			return dao.deleteUser(name);
		}
		return null;
	}
}
