package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.User_Roles;

public interface IUser_RoleDAO {

	public void addOrUpdateUser_Roles(User_Roles user);

	public List<User_Roles> listUser_Roles();

	public void removeUser_Roles(String username);
	
	public User_Roles findByUsername(String username);
}
