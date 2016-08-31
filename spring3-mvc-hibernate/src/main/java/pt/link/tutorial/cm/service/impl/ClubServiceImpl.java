package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IClubDAO;
import pt.link.tutorial.cm.domain.Club;
import pt.link.tutorial.cm.service.IClubService;

@Service
public class ClubServiceImpl implements IClubService{

	@Autowired
	private IClubDAO clubDAO;
	
	@Transactional
	public void addOrUpdateClub(Club club) {
		clubDAO.addOrUpdateClub(club);
	}

	@Transactional
	public List<Club> listClubs() {
		return clubDAO.listClubs();
	}

	@Transactional
	public void removeClub(Integer id) {
		clubDAO.removeClub(id);
	}

	@Transactional
	public Club getClub(Integer id) {
		return clubDAO.getClub(id);
	}
}
