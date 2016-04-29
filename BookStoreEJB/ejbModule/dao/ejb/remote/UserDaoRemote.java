package dao.ejb.remote;

import java.util.List;

import javax.ejb.Remote;

import entityBean.User;

@Remote
public interface UserDaoRemote {
	public void addUser(User user);
	public User getUserByID(int id);
	public User getUserByName(String name);
	public List<User> getUsersByFuzzyName(String name);
	public User updateUserInfo(User user);
}
