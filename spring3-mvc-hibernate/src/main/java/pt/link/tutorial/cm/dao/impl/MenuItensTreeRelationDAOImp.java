package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IMenuItensTreeRelationDAO;
import pt.link.tutorial.cm.domain.MenuItensTreeRelation;

@Repository
public class MenuItensTreeRelationDAOImp implements IMenuItensTreeRelationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addOrUpdateMenuItensTreeRelation(MenuItensTreeRelation menuItensTreeRelation) {
		System.out.println(menuItensTreeRelation.getId());

		sessionFactory.getCurrentSession().saveOrUpdate(menuItensTreeRelation);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MenuItensTreeRelation> listMenuItensTreeRelations() {
		return sessionFactory.getCurrentSession().createQuery("from MenuItensTreeRelation").list();
	}

	@Override
	@Transactional
	public void removeMenuItensTreeRelation(Integer id) {
		MenuItensTreeRelation menuItensTreeRelation = (MenuItensTreeRelation) sessionFactory.getCurrentSession().get(MenuItensTreeRelation.class, id);

		if (menuItensTreeRelation != null) {
			sessionFactory.getCurrentSession().delete(menuItensTreeRelation);
		}
	}

	@Override
	@Transactional
	public MenuItensTreeRelation getMenuItensTreeRelation(Integer id) {
		MenuItensTreeRelation menuItensTreeRelation = (MenuItensTreeRelation) sessionFactory.getCurrentSession().get(MenuItensTreeRelation.class, id);

		return menuItensTreeRelation;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void removeByParent(Integer parentId) {
		List<MenuItensTreeRelation> menuItensTreeRelationList = (List<MenuItensTreeRelation>) sessionFactory.getCurrentSession()
				.createQuery("from MenuItensTreeRelation as treeRelation where treeRelation.parentId = "+parentId).list();
		
		for(MenuItensTreeRelation treeRelationElement : menuItensTreeRelationList){
			sessionFactory.getCurrentSession().delete(treeRelationElement);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void removeByChild(Integer childId) {
		List<MenuItensTreeRelation> menuItensTreeRelationList = (List<MenuItensTreeRelation>) sessionFactory.getCurrentSession()
				.createQuery("from MenuItensTreeRelation as treeRelation where treeRelation.childId = "+childId).list();
		
		for(MenuItensTreeRelation treeRelationElement : menuItensTreeRelationList){
			sessionFactory.getCurrentSession().delete(treeRelationElement);
		}
		
	}

}
