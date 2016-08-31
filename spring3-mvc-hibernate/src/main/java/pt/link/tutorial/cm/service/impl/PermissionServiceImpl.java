package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IPermissionDAO;
import pt.link.tutorial.cm.domain.Permission;
import pt.link.tutorial.cm.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	private IPermissionDAO permissionDAO;
	
	@Transactional
	public void addOrUpdatePermission(Permission permission) {
		permissionDAO.addOrUpdatePermission(permission);
	}

	@Transactional
	public List<Permission> listPermissions() {
		return permissionDAO.listPermissions();
	}

	@Transactional
	public void removePermission(Integer id) {
		permissionDAO.removePermission(id);
	}

	@Transactional
	public Permission getPermission(Integer id) {
		return permissionDAO.getPermission(id);
	}
}
