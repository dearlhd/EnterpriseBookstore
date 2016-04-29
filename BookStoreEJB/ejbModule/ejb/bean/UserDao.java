package ejb.bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        em.persist(user); //持久化实体  
	}

	public User getUserBySql (Query query) {
		List userlist = query.getResultList();
		if (userlist.size() == 0) {
			return null;
		}
		else {
			Object []obj = (Object[]) userlist.get(0);
			User user = new User();
			user.setUsername(obj[0].toString());
			user.setPassword(obj[1].toString());
			user.setAdm(Integer.parseInt(obj[3].toString()));
			user.setAge(Integer.parseInt(obj[4].toString()));
			
			if (obj[5].toString() != null)
				user.setEmail(obj[5].toString());
			return user;
		}
	}
	
	@Override
	public User getUserByID(int id) {
		Query query = em.createNativeQuery("select * from Users Where UserId=:id");
		query.setParameter("id", id);
		
		return getUserBySql(query);

	}

	@Override
	public User getUserByName(String name) {
		Query query = em.createNativeQuery("select * from Users Where username=:username");
		query.setParameter("username", name);
		
		return getUserBySql(query);
	}

	@Override
	public List<User> getUsersByFuzzyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		em.merge(user); //持久化实体
		em.flush();
	}
    
}
