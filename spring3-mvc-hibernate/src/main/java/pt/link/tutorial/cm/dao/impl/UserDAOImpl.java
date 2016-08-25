package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IUserDAO;
import pt.link.tutorial.cm.domain.User;

@Repository(value="userDaoImpl")
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addOrUpdateUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> listUser() {
		return sessionFactory.getCurrentSession().createQuery("from " +User.class.getName()).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> listUsersOrderedByName() {
		return sessionFactory.getCurrentSession().createQuery("from "+User.class.getName() + " order by name asc").list();
	}
	
	@Override
	@Transactional
	public void removeUser(String username) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, username);
		
		if (user != null) {
			sessionFactory.getCurrentSession().delete(user);
		}	
	}
	
	@Override
	@Transactional
	public void changePermissions(Integer id) {
		sessionFactory.getCurrentSession().createQuery("from " +User.class.getName() +" ");
	}
	
	@Override
	@Transactional
	public User findByUsername(String username){
		List<User> users = listUser();
		
		for(User user : users){
			if(user.getUsername().equals(username)){
				return user;
			}
		}
		
		return null;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}