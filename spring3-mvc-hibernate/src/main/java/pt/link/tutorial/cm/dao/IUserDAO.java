package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.User;

public interface IUserDAO {
	public void addOrUpdateUser(User user);

	public List<User> listUser();
	
	public List<User> listUsersOrderedByName();

	public void removeUser(String username);
	
	public void changePermissions(Integer id);
	
	public User findByUsername(String username);
	
}
