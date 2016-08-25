package pt.link.tutorial.cm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IUserDAO;
import pt.link.tutorial.cm.dao.IUser_RoleDAO;
import pt.link.tutorial.cm.domain.User_Roles;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUser_RoleDAO user_roleDAO;
	
	@Autowired IUserDAO userDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		pt.link.tutorial.cm.domain.User user = userDAO.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("No user found with username: " + username);
		List<GrantedAuthority> authorities = buildUserAuthority(user_roleDAO.findByUsername(username));
		return buildUserForAuthentication(user, authorities);
	}
	
	private User buildUserForAuthentication(pt.link.tutorial.cm.domain.User user, List<GrantedAuthority> authorities){
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(User_Roles userRole){
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	public IUserDAO getUserDao() {
		return userDAO;
	}

	public void setUserDao(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
