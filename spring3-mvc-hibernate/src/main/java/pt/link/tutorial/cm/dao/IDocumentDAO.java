package pt.link.tutorial.cm.dao;

import java.util.List;

import pt.link.libraries.data.dao.IPageableHibernateSpringDao;
import pt.link.libraries.hashmapfilter.HashMapFilter;
import pt.link.tutorial.cm.domain.Document;

public interface IDocumentDAO extends IPageableHibernateSpringDao<Document, Long>{
	
	public void addOrUpdateDocument(Document document);

	public List<Document> getListDocuments();

	public void removeDocument(Integer id);

	public Document getDocument(Integer id);

	public List<Document> getListaDocumentos(HashMapFilter filter);
	

}
