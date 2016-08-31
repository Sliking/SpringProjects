package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.tutorial.cm.dao.IMenuItemDAO;
import pt.link.tutorial.cm.domain.MenuItem;

@Repository
public class MenuItemDAOImpl implements IMenuItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addOrUpdateMenuItem(MenuItem menuItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(menuItem);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MenuItem> listMenuItem() {
		return sessionFactory.getCurrentSession().createQuery("from MenuItem").list();
	}

	@Override
	@Transactional
	public void removeMenuItem(Integer id) {
		MenuItem menuItem = (MenuItem) sessionFactory.getCurrentSession().get(MenuItem.class, id);
		
		if (menuItem != null) {
			sessionFactory.getCurrentSession().delete(menuItem);
		}
	}

	@Override
	@Transactional
	public MenuItem getMenuItem(Integer id) {
		MenuItem menuItem = (MenuItem) sessionFactory.getCurrentSession().get(MenuItem.class, id);
		
		return menuItem;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MenuItem> getFirstLevelMenuItemList() {

		String firstLevelMenuItemListHQLQuery = "from MenuItem as menuItem "
													+"where not exists ( select 1 "
															+"from MenuItensTreeRelation as treeRelation "
															+"inner join treeRelation.childItems as ci "
															+ "where ci.id = menuItem.id "
													+")";

		return (List<MenuItem>) sessionFactory.getCurrentSession().createQuery(firstLevelMenuItemListHQLQuery).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MenuItem> getMenuItemChildsListByParentId(Integer id) {
		
		String menuItemChildsListByParentIdHQLQuery = "select treeRelation.childItems "
														+ "from MenuItensTreeRelation as treeRelation "
															+"inner join treeRelation.parentItem as parent "
															+"where parent.id = " + id.intValue() +" "
													+"";
		
		return (List<MenuItem>) sessionFactory.getCurrentSession().createQuery(menuItemChildsListByParentIdHQLQuery).list();

	}

}
