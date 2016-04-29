package ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.User;

@Remote
public interface UserRemote {
	public User login(User user);
	public User register(User user);
	public User searchUserById(int i);
	public User searchUserByName(String name);
	public List<User> searchUsersByFuzzyName(String name);
	public User updateUserInfo(User user);
	
	public String sayHello(String str);
	public User retUser (int n);
}
