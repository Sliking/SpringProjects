package pt.link.tutorial.cm.dao;

import java.util.List;
import pt.link.tutorial.cm.domain.Club;

public interface IClubDAO {
	
	public void addOrUpdateClub(Club club);

	public List<Club> listClubs();

	public void removeClub(Integer id);

	public Club getClub(Integer id);
}
