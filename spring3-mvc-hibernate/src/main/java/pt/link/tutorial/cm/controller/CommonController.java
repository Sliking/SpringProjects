package pt.link.tutorial.cm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import pt.link.tutorial.cm.dto.MenuItemDTO;
import pt.link.tutorial.cm.service.IMenuItemService;

/**
 * 
 * @author adilson.cabral
 * All controllers should extend this class
 * so there is no need set the ModelAtrribute menu
 */

public abstract class CommonController {
	
	protected static final Logger LOG = Logger.getLogger(CommonController.class);

	@Autowired
	protected IMenuItemService menuItemService;

	/**
	 * @return List<MenuItemDTO>
	 */
	@ModelAttribute("menu")
	public List<MenuItemDTO> getMenu(){

		List<MenuItemDTO> toReturn = new ArrayList<MenuItemDTO>();
		
		MenuItemDTO menuItem = new MenuItemDTO();
		
		menuItem.setId(0);
		menuItem.setLabel("Link Tables");
		menuItem.setHref("linktable.html");
		menuItem.setPosition(3);
		toReturn.add(menuItem);
		
		menuItem = new MenuItemDTO();
		menuItem.setId(1);
		menuItem.setLabel("Gestor Menu");
		menuItem.setHref("menuItemList.html");
		menuItem.setPosition(1);
		toReturn.add(menuItem);
		
		/* Entrada adicionada ao menu */
		
		menuItem = new MenuItemDTO();
		menuItem.setId(2);
		menuItem.setLabel("Melhores Clubes");
		menuItem.setHref("clubs.html");
		menuItem.setPosition(2);
		toReturn.add(menuItem);
		
		/* -------------------------- */
		
		/* Entrada de administração */
		
		menuItem = new MenuItemDTO();
		menuItem.setId(3);
		menuItem.setLabel("Administration");
		menuItem.setHref("");
		menuItem.setPosition(0);
		toReturn.add(menuItem);
		
			MenuItemDTO upper_lvl = menuItem;
			
			/* Sub-Entrada Utilizador */

			menuItem = new MenuItemDTO();
			menuItem.setId(4);
			menuItem.setLabel("Users");
			menuItem.setHref("users.html");
			menuItem.setPosition(4);
			menuItem.setParentId(upper_lvl.getId());
			upper_lvl.getChilds().add(menuItem);
			
			/* Sub-Entrada Perfis */
			
			menuItem = new MenuItemDTO();
			menuItem.setId(5);
			menuItem.setLabel("Profiles");
			menuItem.setHref("profiles.html");
			menuItem.setPosition(5);
			menuItem.setParentId(upper_lvl.getId());
			upper_lvl.getChilds().add(menuItem);		
		
		if(LOG.isInfoEnabled()) {
			LOG.info("[Info] Model Atribute menu set: "+toReturn.toString());
		}

		return toReturn;
	}
	

}
