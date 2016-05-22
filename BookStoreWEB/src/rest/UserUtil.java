package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import entityBean.User;
import net.sf.json.JSONObject;


@Path("account/")
public class UserUtil {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> allUser() {
		List<User> lu = new ArrayList<User>();
		User user = new User();
		user.setPassword("123");
		user.setUsername("aaa");
		lu.add(user);
		lu.add(user);
		return lu;
	}

	@Path("{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String name) {
		System.out.println("HTTP GET");

		User user = new User();
		user.setPassword("123");
		user.setUsername(name);
		return user;
	}

	@Path("{username}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User postUser(String obj) {
		User user = new User();
		try {
			JSONObject jo = JSONObject.fromObject(obj);
			user = (User)JSONObject.toBean(jo, User.class);
		} catch (ClassCastException e) {
			System.out.println("class cast exception");
			e.printStackTrace();
			throw new WebApplicationException(400);
		} catch (Exception e) {
			System.out.println("exception");
			e.printStackTrace();
			throw new WebApplicationException(400);
		}
		
		return user;
	}
}