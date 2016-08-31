package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.Profile;

public interface IProfileDAO {
	
	public void addOrUpdateProfile(Profile profile);

	public List<Profile> listProfiles();

	public void removeProfile(Integer id);

	public Profile getProfile(Integer id);
}
