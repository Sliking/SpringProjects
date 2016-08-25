package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IPermissionDAO;
import pt.link.tutorial.cm.domain.Permission;

@Repository
public class PermissionDAOImpl implements IPermissionDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdatePermission(Permission permission) {
		sessionFactory.getCurrentSession().saveOrUpdate(permission);		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Permission> listPermissions() {
		return sessionFactory.getCurrentSession().createQuery("from Permission").list();
	}

	@Override
	@Transactional
	public void removePermission(Integer id) {
		Permission permission = (Permission) sessionFactory.getCurrentSession().get(Permission.class, id);
		
		if (permission != null) {
			sessionFactory.getCurrentSession().delete(permission);
		}		
	}

	@Override
	@Transactional
	public Permission getPermission(Integer id) {
		return (Permission) sessionFactory.getCurrentSession().get(Permission.class, id);
	}
	
	
}
