package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.User;

public interface IUserService {
	
	public void addOrUpdateUser(User user);

	public List<User> listUsers();
	
	public List<User> listUsersOrderedByName();

	public void removeUser(String username);

	public User findByUsername(String username);
	
}
