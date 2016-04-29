package DAOImpl;

import javax.persistence.*;

import entityBean.User;
import DAO.UserDao;

public class UserDaoImpl implements UserDao{
	@PersistenceContext(unitName="BookStoreEJB")
	private EntityManager em;
	
	@Override
	public void addUser(User user) {
        em.getTransaction().begin();  
        em.persist(user); //�־û�ʵ��  
        em.getTransaction().commit();  
	}}
