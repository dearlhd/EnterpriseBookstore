package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.User;

@Remote
public interface UserManager {
	public User login(User user);
	public User register(User user);
	public User searchUserById(int n);
	public User searchUserByName(String name);
	public List<User> searchUsersByFuzzyName(String name);
	public User updateUserInfo(User user);
	public User deleteUser (String name);
}
