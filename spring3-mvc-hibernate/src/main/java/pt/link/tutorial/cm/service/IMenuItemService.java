package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.MenuItem;
import pt.link.tutorial.cm.dto.MenuItemDTO;

public interface IMenuItemService {
	public void addOrUpdateMenuItem(MenuItem menuItem);

	public List<MenuItem> listMenuItem();

	public void removeMenuItem(Integer id);

	public MenuItem getMenuItem(Integer id);
	
	public List<MenuItemDTO> getFirstLevelMenuItemList();
	
	public List<MenuItemDTO> getMenuItemChildsList(MenuItemDTO menuItem);

	public List<MenuItemDTO> getMenu();
}
