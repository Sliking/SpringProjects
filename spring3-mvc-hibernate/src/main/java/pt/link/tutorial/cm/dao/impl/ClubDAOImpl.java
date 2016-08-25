package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IClubDAO;
import pt.link.tutorial.cm.domain.Club;

@Repository
public class ClubDAOImpl implements IClubDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdateClub(Club club) {
		sessionFactory.getCurrentSession().saveOrUpdate(club);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Club> listClubs() {
		return sessionFactory.getCurrentSession().createQuery("from Club").list();
	}

	@Override
	@Transactional
	public void removeClub(Integer id) {
		Club club = (Club) sessionFactory.getCurrentSession().get(Club.class, id);
		
		if (club != null) {
			sessionFactory.getCurrentSession().delete(club);
		}	
	}
	
	@Override
	@Transactional
	public Club getClub(Integer id) {
		return (Club) sessionFactory.getCurrentSession().get(Club.class, id);
	}
}
