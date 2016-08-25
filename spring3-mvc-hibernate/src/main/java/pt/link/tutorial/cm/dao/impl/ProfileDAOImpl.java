package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IProfileDAO;
import pt.link.tutorial.cm.domain.Profile;

@Repository
public class ProfileDAOImpl implements IProfileDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdateProfile(Profile profile) {
		sessionFactory.getCurrentSession().saveOrUpdate(profile);	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Profile> listProfiles() {
		return sessionFactory.getCurrentSession().createQuery("from Profile").list();
	}

	@Override
	@Transactional
	public void removeProfile(Integer id) {
		Profile profile = (Profile) sessionFactory.getCurrentSession().get(Profile.class, id);
		
		if (profile != null) {
			sessionFactory.getCurrentSession().delete(profile);
		}
	}

	@Override
	@Transactional
	public Profile getProfile(Integer id) {
		return (Profile) sessionFactory.getCurrentSession().get(Profile.class, id);
	}

}
