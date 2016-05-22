package rest;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import ejb.bean.UserBean;
import ejb.remote.UserManager;
import entityBean.User;
import net.sf.json.JSONObject;


@Path("account/")
public class UserUtil {
	UserManager um;
	
	private void initUserManager () {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			final Context context = new InitialContext(jndiProperties);    
		    um = (UserManager) context.lookup("ejb:/BookStoreEJB//UserBean!ejb.remote.UserManager");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> allUser() {
		initUserManager ();
		List<User> lu = um.searchUsersByFuzzyName("");
		return lu;
	}

	@Path("{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String name) {
		initUserManager ();
		
		User user = um.searchUserByName(name);
		return user;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User postUser(String obj) {
		initUserManager ();
		try {
			User user = new User();
			JSONObject jo = JSONObject.fromObject(obj);
			user = (User)JSONObject.toBean(jo, User.class);
			checkHttpBody(jo);
			
			if (user.getUsername() == null || user.getUsername().equals("")) {	
				throw new Exception();
			}
			
			if (um.searchUserByName(user.getUsername()) != null) { 
				throw new Exception();
			}
			
			return um.register(user);
			//return user;
			
		} catch (ClassCastException e) {
			System.out.println("class cast exception");
			e.printStackTrace();
			throw new WebApplicationException(400);
		} catch (Exception e) {
			System.out.println("exception");
			//e.printStackTrace();
			throw new WebApplicationException(400);
		}
	}
	
	@Path("{username}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User putUser(String obj, @PathParam("username") String name) {
		initUserManager ();
		try {
			User user = new User();
			JSONObject jo = JSONObject.fromObject(obj);
			user = (User)JSONObject.toBean(jo, User.class);
			checkHttpBody(jo);
			
			User userInDB = um.searchUserByName(name);
			if (!user.getUsername().equals(userInDB.getUsername())) throw new Exception();
			if (user.getUsername() == null) user.setUsername(userInDB.getUsername());
			if (user.getPassword() == null) user.setPassword(userInDB.getPassword());
			if (user.getAge() == 0) user.setAge(userInDB.getAge());
			if (user.getEmail() == null) user.setEmail(userInDB.getEmail());
			
			return user;
		} catch (ClassCastException e) {
			System.out.println("class cast exception");
			e.printStackTrace();
			throw new WebApplicationException(400);
		} catch (Exception e) {
			System.out.println("exception");
			//e.printStackTrace();
			throw new WebApplicationException(400);
		}
	}
	
	@Path("{username}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public User deleteUser (@PathParam("username") String name) {
		initUserManager ();
		try {
			if (um.searchUserByName(name) == null) 
				throw new Exception();
			
			return um.deleteUser(name);
			
		} catch (Exception e) {
			throw new WebApplicationException(400);
		}
	}
	
	
	private void checkHttpBody (JSONObject jo) throws Exception {
		if (jo.containsKey("username")) jo.remove("username");
		if (jo.containsKey("password")) jo.remove("password");
		if (jo.containsKey("age")) jo.remove("age");
		if (jo.containsKey("adm")) jo.remove("adm");
		if (jo.containsKey("userId")) jo.remove("userId");
		if (jo.containsKey("email")) jo.remove("email");
		
		if (!jo.isEmpty()) 
			throw new Exception(); 
	}
}