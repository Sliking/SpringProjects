package pt.link.tutorial.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.link.libraries.data.dao.IPageableHibernateSpringDao;
import pt.link.libraries.data.service.impl.PageableService;
import pt.link.libraries.hashmapfilter.HashMapFilter;
import pt.link.libraries.pagging.Page;
import pt.link.libraries.pagging.PageInfo;
import pt.link.tutorial.cm.dao.IDocumentDAO;
import pt.link.tutorial.cm.domain.Document;
import pt.link.tutorial.cm.dto.DocumentDTO;
import pt.link.tutorial.cm.service.IDocumentService;

@Service
public class DocumentServiceImpl extends PageableService<Document, Long> implements IDocumentService{

	@Autowired
	private IDocumentDAO documentDAO;
	
	@Override
	@Transactional
	public void addOrUpdateDocument(DocumentDTO document) {
		documentDAO.addOrUpdateDocument(convert(document));
	}

	@Override
	@Transactional
	public List<DocumentDTO> getListDocuments() {
		return convert(documentDAO.getListDocuments());
	}

	@Override
	@Transactional
	public void removeDocument(Integer id) {
		documentDAO.removeDocument(id);	
	}

	@Override
	@Transactional
	public DocumentDTO getDocument(Integer id) {
		return convert(documentDAO.getDocument(id));
	}
	
	@Override
	@Transactional
	public Page<DocumentDTO> getPageDocuments(HashMapFilter filter, PageInfo pageInfo) {
		return documentDAO.findByHqlFilter(filter, pageInfo);
	}
	
	@Override
	@Transactional
	public IPageableHibernateSpringDao<Document, Long> getBaseDao() {
		
		return documentDAO;
		
	}

	@Override
	@Transactional
	public List<DocumentDTO> getListaDocumentos(HashMapFilter filter) {
		 return convert(documentDAO.getListaDocumentos(filter));
	}
	
	//-------------------------
	
	private Document convert(DocumentDTO document){
		Document toReturn = new Document();
		
		toReturn.setId(document.getId());
		toReturn.setAuthor(document.getAuthor());
		toReturn.setLength(document.getLength());
		toReturn.setExtention(document.getExtention());

		return toReturn;
	}
	
	private DocumentDTO convert(Document document){
		DocumentDTO toReturn = new DocumentDTO();
		
		toReturn.setId(document.getId());
		toReturn.setAuthor(document.getAuthor());
		toReturn.setLength(document.getLength());
		toReturn.setExtention(document.getExtention());

		return toReturn;
	}
	
	private List<DocumentDTO> convert(List<Document> documents){
		List<DocumentDTO> toReturn = new ArrayList<DocumentDTO>();
		
		for(Document currentElement: documents){
			toReturn.add(convert(currentElement));
		}
		return toReturn;
	}
}