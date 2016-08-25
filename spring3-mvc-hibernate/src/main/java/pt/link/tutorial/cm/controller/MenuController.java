package pt.link.tutorial.cm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.link.tutorial.cm.domain.MenuItem;
import pt.link.tutorial.cm.domain.MenuItensTreeRelation;
import pt.link.tutorial.cm.dto.MenuItemDTO;
import pt.link.tutorial.cm.service.IMenuItensTreeRelationService;
import pt.link.tutorial.cm.validator.MenuItemDTOValidator;

@Controller
public class MenuController extends CommonController{
	
	
	@Autowired
	private IMenuItensTreeRelationService menuItensTreeRelationService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MenuItemDTOValidator());
	}
	
	@RequestMapping(value = "/menuItemDetail")
	public String getMenuItemDetail(@RequestParam("menuItemId") Integer menuItemId, ModelMap model){
		
		MenuItem menuItem = menuItemService.getMenuItem(menuItemId);
		
		if (menuItem != null)
			model.put("menuItem", convert(menuItem));
		
		populateModel(model);
		
		return "menuItemDetail";
	}
	
	@RequestMapping(value = "/menuItemList")
	public String menuItemList(ModelMap model){
		MenuItemDTO menuItemDTO = new MenuItemDTO();		
		model.put("menuItem", menuItemDTO);
		
		populateModel(model);
		
		return "menuItemList";
	}
	
	@RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
	public String addMenuItem(@Valid @ModelAttribute("menuItem") MenuItemDTO menuItemDTO, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("menuItem", menuItemDTO);
			
			populateModel(model);
			
			return "menuItemList";
		}

		menuItemService.addOrUpdateMenuItem(convert(menuItemDTO));

		return "redirect:/menuItemList.html";
	}
	
	@RequestMapping(value = "/updateMenuItem", method = RequestMethod.POST)
	public String updateMenuItem(@Valid @ModelAttribute("menuItem") MenuItemDTO menuItemDTO, BindingResult result, ModelMap model){
		if (result.hasErrors()) {
			model.put("menuItem", menuItemDTO);

			model.put("errorAddOrUpdateMenuItem", "errorUpdateMenuItem");
			
			populateModel(model);
			
			return "menuItemList";
		}

		menuItemService.addOrUpdateMenuItem(convert(menuItemDTO));

		return "redirect:/menuItemList.html";
	}
	
	@RequestMapping(value = "/addChildMenuItem", method = RequestMethod.POST)
	public String addChildMenuItem(@Valid @ModelAttribute("menuItem") MenuItemDTO menuItemDTO, BindingResult result, ModelMap model){
		if (result.hasErrors()) {
			model.put("menuItem", menuItemDTO);
			
			populateModel(model);
			
			model.put("errorAddOrUpdateMenuItem", "errorAddChildMenuItem");
			
			return "menuItemList";
		}
		
		MenuItem parentMenuItem = menuItemService.getMenuItem(menuItemDTO.getParentId());

		MenuItem newMenuItem = convert(menuItemDTO);
		menuItemService.addOrUpdateMenuItem(newMenuItem);
				
		MenuItensTreeRelation newMenuItensTreeRelation = new MenuItensTreeRelation();
		
		newMenuItensTreeRelation.setParentId(parentMenuItem.getId());
		newMenuItensTreeRelation.setChildId(newMenuItem.getId());
		
		menuItensTreeRelationService.addOrUpdateMenuItensTreeRelation(newMenuItensTreeRelation);

		return "redirect:/menuItemList.html";
	}
	
	@RequestMapping(value = "/deleteMenuItem")
	@Transactional
	public String deleteMenuItem(@RequestParam("menuItemID") Integer menuItemId, ModelMap model){
		menuItemService.removeMenuItem(menuItemId);

		return "redirect:/menuItemList.html";
	}

	protected void populateModel(ModelMap model) {

		model.put("menuItemList", getMenu());
	}
	
	private MenuItem convert(MenuItemDTO menuItemDTO) {
		MenuItem menuItem = new MenuItem();
		
		menuItem.setId(menuItemDTO.getId());
		menuItem.setLabel(menuItemDTO.getLabel());
		menuItem.setHref(menuItemDTO.getHref());
		menuItem.setPosition(menuItemDTO.getPosition());
		
		return menuItem;
	}
	
	private MenuItemDTO convert(MenuItem menuItem) {
		MenuItemDTO menuItemDTO = new MenuItemDTO();
		
		menuItemDTO.setId(menuItem.getId());
		menuItemDTO.setLabel(menuItem.getLabel());
		menuItemDTO.setHref(menuItem.getHref());
		menuItemDTO.setPosition(menuItem.getPosition());
		
		return menuItemDTO;
	}
}
