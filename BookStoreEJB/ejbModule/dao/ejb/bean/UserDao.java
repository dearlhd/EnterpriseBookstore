package dao.ejb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.ejb.remote.UserDaoRemote;
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
        em.persist(user); //�־û�ʵ��  
	}

	public List<User> getUsersBySql (Query query) {
		List userlist = query.getResultList();
		if (userlist.size() == 0) {
			return null;
		}
		else {
			List<User> users = new ArrayList<User>();
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
		Query query = em.createNativeQuery("select * from users Where UserId=:id");
		query.setParameter("id", id);
		
		List<User> ul = getUsersBySql(query);
		if (ul == null) {
			return null;
		}
		return ul.get(0);
	}

	@Override
	public User getUserByName(String name) {
		Query query = em.createNativeQuery("select * from users Where username=:username");
		query.setParameter("username", name);
		
		List<User> ul = getUsersBySql(query);
		if (ul == null) {
			return null;
		}
		return ul.get(0);
	}

	@Override
	public List<User> getUsersByFuzzyName(String name) {
		Query query = em.createNativeQuery("select * from users Where username like :username");
		query.setParameter("username", "%"+name+"%");
		
		List<User> ul = getUsersBySql(query);
		if (ul == null) {
			return null;
		}
		return ul;
	}

	@Override
	public User updateUserInfo(User user) {
		User newUser = em.merge(user); //�־û�ʵ��
		em.flush();
		return newUser;
	}

	@Override
	public User deleteUser(String name) {
		User user = getUserByName(name);
		em.remove(em.merge(user));
		return user;
	}
    
}
