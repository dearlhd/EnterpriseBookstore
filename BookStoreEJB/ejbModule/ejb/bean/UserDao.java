package ejb.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.remote.UserDaoRemote;
import entityBean.User;

/**
 * Session Bean implementation class UserDao
 */
@Stateless
@LocalBean
public class UserDao implements UserDaoRemote {
	@PersistenceContext(unitName="BookStoreEJB")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addUser(User user) {
		System.out.println("In dao");
        System.out.println("In dao");
        em.persist(user); //持久化实体  
	}
    
}
