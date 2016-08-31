package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.Permission;

public interface IPermissionService {

	public void addOrUpdatePermission(Permission permission);

	public List<Permission> listPermissions();

	public void removePermission(Integer id);

	public Permission getPermission(Integer id);
}
