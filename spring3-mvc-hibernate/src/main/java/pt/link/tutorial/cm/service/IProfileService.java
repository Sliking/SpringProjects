package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.Profile;

public interface IProfileService {
	
	public void addOrUpdateProfile(Profile profile);

	public List<Profile> listProfiles();

	public void removeProfile(Integer id);

	public Profile getProfile(Integer id);
}
