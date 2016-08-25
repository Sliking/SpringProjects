package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IUserDAO;
import pt.link.tutorial.cm.domain.User;
import pt.link.tutorial.cm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public void addOrUpdateUser(User user) {
		userDAO.addOrUpdateUser(user);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return userDAO.listUser();
	}

	@Override
	@Transactional
	public List<User> listUsersOrderedByName() {
		return userDAO.listUsersOrderedByName();
	}
	
	@Override
	@Transactional
	public void removeUser(String username) {
		userDAO.removeUser(username);
		
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
}