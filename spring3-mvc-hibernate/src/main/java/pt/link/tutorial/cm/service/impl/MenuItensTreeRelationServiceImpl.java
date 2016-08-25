package pt.link.tutorial.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IMenuItensTreeRelationDAO;
import pt.link.tutorial.cm.domain.MenuItensTreeRelation;
import pt.link.tutorial.cm.service.IMenuItensTreeRelationService;

@Service
public class MenuItensTreeRelationServiceImpl implements IMenuItensTreeRelationService {

	@Autowired
	private IMenuItensTreeRelationDAO menuItensTreeRelationDAO;
	
	@Override
	@Transactional
	public void addOrUpdateMenuItensTreeRelation(MenuItensTreeRelation menuItensTreeRelation) {
		menuItensTreeRelationDAO.addOrUpdateMenuItensTreeRelation(menuItensTreeRelation);

	}

	@Override
	@Transactional
	public List<MenuItensTreeRelation> listMenuItensTreeRelations() {
		return menuItensTreeRelationDAO.listMenuItensTreeRelations();
	}

	@Override
	@Transactional
	public void removeMenuItensTreeRelation(Integer id) {
		menuItensTreeRelationDAO.removeMenuItensTreeRelation(id);

	}

	@Override
	@Transactional
	public MenuItensTreeRelation getMenuItensTreeRelation(Integer id) {
		return menuItensTreeRelationDAO.getMenuItensTreeRelation(id);
	}
	
	@Override
	@Transactional
	public void removeByParent(Integer parentId) {
		menuItensTreeRelationDAO.removeByParent(parentId);
		
	}

	@Override
	@Transactional
	public void removeByChild(Integer childId) {
		menuItensTreeRelationDAO.removeByChild(childId);
		
	}
	
	/**
	 * @return the menuItensTreeRelationDAO
	 */
	public IMenuItensTreeRelationDAO getMenuItensTreeRelationDAO() {
		return menuItensTreeRelationDAO;
	}

	/**
	 * @param menuItensTreeRelationDAO the menuItensTreeRelationDAO to set
	 */
	public void setMenuItensTreeRelationDAO(IMenuItensTreeRelationDAO menuItensTreeRelationDAO) {
		this.menuItensTreeRelationDAO = menuItensTreeRelationDAO;
	}

}
