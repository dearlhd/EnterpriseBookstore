package ejb.remote;

import javax.ejb.Remote;

import entityBean.User;

@Remote
public interface UserDaoRemote {
	public void addUser(User user);
}
