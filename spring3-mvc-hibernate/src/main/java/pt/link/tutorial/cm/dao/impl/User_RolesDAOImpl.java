package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IUser_RoleDAO;
import pt.link.tutorial.cm.domain.User_Roles;

@Repository
public class User_RolesDAOImpl implements IUser_RoleDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdateUser_Roles(User_Roles user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User_Roles> listUser_Roles() {
		return sessionFactory.getCurrentSession().createQuery("from " +User_Roles.class.getName()).list();
	}

	@Override
	@Transactional
	public void removeUser_Roles(String username) {
		User_Roles role = (User_Roles) sessionFactory.getCurrentSession().get(User_Roles.class, username);
		
		if (role != null) {
			sessionFactory.getCurrentSession().delete(role);
		}
		
	}

	@Override
	@Transactional
	public User_Roles findByUsername(String username) {
		List<User_Roles> roles = listUser_Roles();
		
		for(User_Roles role : roles){
			if(role.getUsername().equals(username))
				return role;
		}
		return null;
	}

}
