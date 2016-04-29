package ejb.bean;

import ejb.remote.UserRemote;
import entityBean.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import DAO.UserDao;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
public class UserBean implements UserRemote {

	@PersistenceContext(unitName="BookStoreEJB")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean login(String username, String password) {
//		try {
//			// 此处指定了使用配置文件的“Sample"验证模块，对应的实现类为SimpleLoginModule
//			System.out.println("Prepare jaas!");
//			LoginContext lc = new LoginContext("Simple",
//					new SimpleCallbackHandler(username, password));
//
//			System.out.println("context prepared!");
//			// 进行登录操作，如果验证失败会抛出异常
//			lc.login();
//			System.out.println("ok");
//		} catch (LoginException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
		return false;
    }
    
    @Override
    public String sayHello(String str) {
    	System.out.println("hello world");
    	return "hi "+str;
    }
    
    @Override
    public User retUser(int n) {
    	User user= new User();
    	user.setUsername("asd");
    	user.setPassword("123123");
        em.persist(user); //持久化实体  
    	return user;
    }
}
