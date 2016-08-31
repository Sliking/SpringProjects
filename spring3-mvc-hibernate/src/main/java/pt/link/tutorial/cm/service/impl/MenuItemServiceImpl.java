package pt.link.tutorial.cm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IMenuItemDAO;
import pt.link.tutorial.cm.domain.MenuItem;
import pt.link.tutorial.cm.dto.MenuItemDTO;
import pt.link.tutorial.cm.service.IMenuItemService;
import pt.link.tutorial.cm.service.IMenuItensTreeRelationService;

@Service
public class MenuItemServiceImpl implements IMenuItemService {

	@Autowired
	private IMenuItemDAO menuItemDAO;
	
	@Autowired
	private IMenuItensTreeRelationService menuItensTreeRelationService;
	
	@Override
	@Transactional
	public void addOrUpdateMenuItem(MenuItem menuItem) {
		menuItemDAO.addOrUpdateMenuItem(menuItem);

	}

	@Override
	@Transactional
	public List<MenuItem> listMenuItem() {
		return menuItemDAO.listMenuItem();
	}

	@Override
	public void removeMenuItem(Integer id) {
		
		MenuItem menuItem = getMenuItem(id);
		
		MenuItemDTO menuItemDTO = new MenuItemDTO();
		menuItemDTO.setId(menuItem.getId());
		menuItemDTO.setHref(menuItem.getHref());
		menuItemDTO.setLabel(menuItem.getLabel());
		menuItemDTO.setPosition(menuItem.getPosition());
		
		List<MenuItemDTO> listMenuItemDTO = new ArrayList<MenuItemDTO>();
		listMenuItemDTO.add(menuItemDTO);
		
		buildMenuTree(listMenuItemDTO, null);
		
		removeMenuItens(listMenuItemDTO);

	}

	@Override
	@Transactional
	public MenuItem getMenuItem(Integer id) {
		return menuItemDAO.getMenuItem(id);
	}

	/**
	 * @return the menuItemDAO
	 */
	public IMenuItemDAO getMenuItemDAO() {
		return menuItemDAO;
	}

	/**
	 * @param menuItemDAO the menuItemDAO to set
	 */
	public void setMenuItemDAO(IMenuItemDAO menuItemDAO) {
		this.menuItemDAO = menuItemDAO;
	}

	@Override
	@Transactional
	public List<MenuItemDTO> getFirstLevelMenuItemList() {
		
		List<MenuItem> firstLevelMenuItemList = menuItemDAO.getFirstLevelMenuItemList();
		
		List<MenuItemDTO> toReturn = convertMenuItemListToMenuItemDTOList(firstLevelMenuItemList);
		
		Collections.sort(toReturn);
		
		return toReturn;
	}

	@Override
	@Transactional
	public List<MenuItemDTO> getMenuItemChildsList(MenuItemDTO menuItem) {
		
		List<MenuItem> menuItemChildsList = menuItemDAO.getMenuItemChildsListByParentId(menuItem.getId());
		
		List<MenuItemDTO> toReturn = convertMenuItemListToMenuItemDTOList(menuItemChildsList);
		
		Collections.sort(toReturn);
		
		return toReturn;
	}
	
	@Override
	@Transactional
	public List<MenuItemDTO> getMenu() {
		List<MenuItemDTO> listToReturn = getFirstLevelMenuItemList();

		buildMenuTree(listToReturn, null);
		
		return listToReturn;
	}
	
	/* Useful private methods */
	private List<MenuItemDTO> convertMenuItemListToMenuItemDTOList(List<MenuItem> menuItemList){
		List<MenuItemDTO> listToReturn = new ArrayList<MenuItemDTO>();
		MenuItemDTO currentMenuItemDTO;
		for(MenuItem currentListElement : menuItemList) {
			currentMenuItemDTO = new MenuItemDTO();
			
			currentMenuItemDTO.setId(currentListElement.getId());
			currentMenuItemDTO.setPosition(currentListElement.getPosition());
			currentMenuItemDTO.setHref(currentListElement.getHref());
			currentMenuItemDTO.setLabel(currentListElement.getLabel());

			listToReturn.add(currentMenuItemDTO);
		}
		
		return listToReturn;
	}

	private void buildMenuTree(List<MenuItemDTO> menuItensList, Integer parentId){
		
		List<MenuItemDTO> listElementChilds;
		for(MenuItemDTO listElement : menuItensList){
			listElement.setParentId(parentId);
			
			listElementChilds = getMenuItemChildsList(listElement);
			listElement.setChilds(listElementChilds);
			
			if(!listElementChilds.isEmpty()){
				buildMenuTree(listElementChilds, listElement.getId());
			}
		}
	}
	
	@Transactional
	private void removeMenuItens(List<MenuItemDTO> menuItensList){
		List<MenuItemDTO> listElementChilds;
		for(MenuItemDTO listElement : menuItensList){
			
			listElementChilds = listElement.getChilds();
			
			if(!listElementChilds.isEmpty()){
				removeMenuItens(listElementChilds);
			}
			
			menuItensTreeRelationService.removeByChild(listElement.getId());
			menuItemDAO.removeMenuItem(listElement.getId());
		}
	}
}
