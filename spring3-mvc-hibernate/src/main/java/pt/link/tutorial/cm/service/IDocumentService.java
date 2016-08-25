package pt.link.tutorial.cm.service;

import java.util.List;

import pt.link.libraries.hashmapfilter.HashMapFilter;
import pt.link.libraries.pagging.Page;
import pt.link.libraries.pagging.PageInfo;
import pt.link.tutorial.cm.dto.DocumentDTO;

public interface IDocumentService {
	
	public void addOrUpdateDocument(DocumentDTO document);

	public List<DocumentDTO> getListDocuments();

	public void removeDocument(Integer id);

	public DocumentDTO getDocument(Integer id);

	public Page<DocumentDTO> getPageDocuments(HashMapFilter filter, PageInfo p);

	public List<DocumentDTO> getListaDocumentos(HashMapFilter filter);
}
