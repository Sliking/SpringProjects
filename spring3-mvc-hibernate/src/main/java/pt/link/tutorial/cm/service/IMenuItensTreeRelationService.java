package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.tutorial.cm.domain.MenuItensTreeRelation;

public interface IMenuItensTreeRelationService {
	
	public void addOrUpdateMenuItensTreeRelation(MenuItensTreeRelation menuItensTreeRelation);

	public List<MenuItensTreeRelation> listMenuItensTreeRelations();

	public void removeMenuItensTreeRelation(Integer id);

	public MenuItensTreeRelation getMenuItensTreeRelation(Integer id);
	
	public void removeByParent(Integer parentId);
	
	public void removeByChild(Integer childId);

}