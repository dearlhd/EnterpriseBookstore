package ejb.bean;

import java.util.ArrayList;
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

	public List<User> getUsersBySql (Query query) {
		List userlist = query.getResultList();
		if (userlist.size() == 0) {
			return null;
		}
		else {
			List<User> users = new ArrayList();
			for (int i = 0; i < userlist.size(); i++) {
				Object []obj = (Object[]) userlist.get(i);
				User user = new User();
				user.setUsername(obj[0].toString());
				user.setPassword(obj[1].toString());
				user.setAdm(Integer.parseInt(obj[3].toString()));
				user.setAge(Integer.parseInt(obj[4].toString()));
				
				if (obj[5].toString() != null)
					user.setEmail(obj[5].toString());
				
				users.add(user);
			}
			
			return users;
		}
	}
	
	@Override
	public User getUserByID(int id) {
		Query query = em.createNativeQuery("select * from Users Where UserId=:id");
		query.setParameter("id", id);
		
		return getUsersBySql(query).get(0);

	}

	@Override
	public User getUserByName(String name) {
		Query query = em.createNativeQuery("select * from Users Where username=:username");
		query.setParameter("username", name);
		
		return getUsersBySql(query).get(0);
	}

	@Override
	public List<User> getUsersByFuzzyName(String name) {
		Query query = em.createNativeQuery("select * from Users Where username like :username");
		query.setParameter("username", "%"+name+"%");
		return getUsersBySql(query);
	}

	@Override
	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		em.merge(user); //持久化实体
		em.flush();
	}
    
}
