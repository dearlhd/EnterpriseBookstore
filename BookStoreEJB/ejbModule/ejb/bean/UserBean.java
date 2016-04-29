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
//			// �˴�ָ����ʹ�������ļ��ġ�Sample"��֤ģ�飬��Ӧ��ʵ����ΪSimpleLoginModule
//			System.out.println("Prepare jaas!");
//			LoginContext lc = new LoginContext("Simple",
//					new SimpleCallbackHandler(username, password));
//
//			System.out.println("context prepared!");
//			// ���е�¼�����������֤ʧ�ܻ��׳��쳣
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
        em.persist(user); //�־û�ʵ��  
    	return user;
    }
}
