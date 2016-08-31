package pt.link.tutorial.cm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.link.libraries.data.dao.impl.PageableHibernateSpringDao;
import pt.link.libraries.hashmapfilter.HashMapFilter;
import pt.link.tutorial.cm.dao.IDocumentDAO;
import pt.link.tutorial.cm.domain.Document;

@Repository
public class DocumentDAOImpl extends PageableHibernateSpringDao<Document, Long> implements IDocumentDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addOrUpdateDocument(Document document) {
		sessionFactory.getCurrentSession().saveOrUpdate(document);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Document> getListDocuments() {
		return sessionFactory.getCurrentSession().createQuery("from Document").list();
	}

	@Override
	@Transactional
	public void removeDocument(Integer id) {
		Document document = (Document) sessionFactory.getCurrentSession().get(Document.class, id);
		
		if (document != null) {
			sessionFactory.getCurrentSession().delete(document);
		}

	}

	@Override
	@Transactional
	public Document getDocument(Integer id) {
		return (Document) sessionFactory.getCurrentSession().get(Document.class, id);

	}

	@Override
	@Transactional
	public List<Document> getListaDocumentos(HashMapFilter filter) {
		return this.findByHqlFilter(filter);
	}

}
