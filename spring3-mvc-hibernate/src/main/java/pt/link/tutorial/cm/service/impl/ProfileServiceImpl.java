package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IProfileDAO;
import pt.link.tutorial.cm.domain.Profile;
import pt.link.tutorial.cm.service.IProfileService;

@Service
public class ProfileServiceImpl implements IProfileService{

	@Autowired
	private IProfileDAO profileDAO;
	
	@Transactional
	public void addOrUpdateProfile(Profile profile) {
		profileDAO.addOrUpdateProfile(profile);
	}

	@Transactional
	public List<Profile> listProfiles() {
		return profileDAO.listProfiles();
	}

	@Transactional
	public void removeProfile(Integer id) {
		profileDAO.removeProfile(id);
	}

	@Transactional
	public Profile getProfile(Integer id) {
		return profileDAO.getProfile(id);
	}
}
