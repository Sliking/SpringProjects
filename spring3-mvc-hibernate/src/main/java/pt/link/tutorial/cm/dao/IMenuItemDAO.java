package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.tutorial.cm.domain.MenuItem;

public interface IMenuItemDAO {
	
	public void addOrUpdateMenuItem(MenuItem menuItem);

	public List<MenuItem> listMenuItem();

	public void removeMenuItem(Integer id);

	public MenuItem getMenuItem(Integer id);

	public List<MenuItem> getFirstLevelMenuItemList();

	public List<MenuItem> getMenuItemChildsListByParentId(Integer id);
}
