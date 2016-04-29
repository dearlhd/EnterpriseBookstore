package ejb.remote;

import javax.ejb.Remote;

import entityBean.User;

@Remote
public interface UserRemote {
	public boolean login(String name, String psw);
	public String sayHello(String str);
	public User retUser (int n);
}
