package action;

import com.opensymphony.xwork2.ActionSupport;

import entityBean.User;

public class UserActions extends ActionSupport{
	User user;
	String action;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String execute() {
		System.out.println("Hello");
		return "success";
	}
}
