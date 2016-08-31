package pt.link.tutorial.cm.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.link.libraries.hashmapfilter.HashMapFilter;
import pt.link.libraries.hashmapfilter.support.FilterProperty;
import pt.link.libraries.hashmapfilter.support.SearchType;
import pt.link.libraries.linktables.ui.ColumnsProperties;
import pt.link.libraries.linktables.ui.LinkTable;
import pt.link.libraries.linktables.ui.TablePopulator;
import pt.link.libraries.pagging.Page;
import pt.link.libraries.pagging.PageInfo;
import pt.link.tutorial.cm.dto.DocumentDTO;
import pt.link.tutorial.cm.dto.DocumentDTOList;
import pt.link.tutorial.cm.service.IDocumentService;
import pt.link.tutorial.cm.util.CustomDate;
import pt.link.tutorial.cm.util.CustomDatePropertyEditor;

@Controller
public class LinkTableController extends CommonController{

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private LinkTable<DocumentDTO> tableDocument;
	private TablePopulator<DocumentDTO> populateDocument;
	private DateFormat dateFormater = new SimpleDateFormat(DATE_PATTERN, Locale.UK);
	
	private List<DocumentDTO> fakeList = fakeList();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(CustomDate.class, new CustomDatePropertyEditor(DATE_PATTERN));
	}

	@Autowired
	private IDocumentService documentService;

	@ModelAttribute("documentDTOList")
	public DocumentDTOList getDocumentDTOList(){
		return new DocumentDTOList();
	}
	
	@ModelAttribute("document")
	public DocumentDTO getDocument(){
		return new DocumentDTO();
	}
	
	@RequestMapping(value = "/documentUpdate", method = RequestMethod.GET)
	public String getDocumentDetail(@RequestParam("documentId") Integer documentId, Model model) {

		DocumentDTO document = new DocumentDTO();
		
		document.setId(9999);
		document.setLength(9999);
		document.setExtention("fake");
		document.setAuthor("Fake");
		try {
			document.setCreationDate(new CustomDate(dateFormater.parse("1000-10-10")));
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		if (document != null) {
			model.addAttribute("document", document);
		}
		
		LinkTableModelAttribute(null, model);
		
		return "linktable";
	}

	@RequestMapping(value = "/editDocuments", method = RequestMethod.POST)
	public String editDocuments(@ModelAttribute("documentDTOList") DocumentDTOList documentDTOList, Model model){

		if(documentDTOList.getDocumentsDTO() != null
				&& !documentDTOList.getDocumentsDTO().isEmpty()){
			for(DocumentDTO documentElement : documentDTOList.getDocumentsDTO()){
				fakeList.add(documentElement);
			}
		}

		LinkTableModelAttribute(null, model);

		return "linktable";
	}
	
	@RequestMapping(value = "/documentDelete")
	public String documentDelete(@RequestParam("documentId") Integer documentId, ModelMap model){
		
		documentService.removeDocument(documentId);
		
		return "linktable";
    }

	@RequestMapping(value = "/linktable")
	public String getLinktTable(DocumentDTO documentDTO,  Model model) {

		LinkTableModelAttribute(documentDTO, model);

		return "linktable";
	}
	
	@RequestMapping(value = "/addOrUpdateDocument", method = RequestMethod.POST)
	public String addOrUpdateContact(@Valid @ModelAttribute("document") DocumentDTO document, BindingResult result, Model model) {
		
		if (result.hasErrors())
			model.addAttribute("document", document);

		LinkTableModelAttribute(null, model);

		fakeList.add(document); LinkTableModelAttribute(null, model); document.setId(666);

		return "linktable";
	}

	private LinkTable<DocumentDTO> createLinkTable(){
		// Utilizar servico para obter objecto
		LinkTable<DocumentDTO> tableDocument = new LinkTable<DocumentDTO>(
				"document", "table.title",
				"table.summary", "table.caption");

		// Nomes dos getters das variaveis a apresentar
		String[] columns = {"id", "author", "length", "extention", "creationDate"};

		tableDocument.setColumnsNames(columns);

		tableDocument.addColumnProperties("id", new ColumnsProperties(
				"id", "table.headername.id"));

		tableDocument.addColumnProperties("length", new ColumnsProperties(
				"size", "table.headername.length"));

		tableDocument.addColumnProperties("author", new ColumnsProperties(
				"author", "table.headername.author"));

		tableDocument.addColumnProperties("extention", new ColumnsProperties(
				"extention", "table.headername.extention"));

		tableDocument.addColumnProperties("creationDate", new ColumnsProperties(
				"creationDate", "table.headername.creationDate"));
		
		tableDocument.setIdFieldName("id");

		return tableDocument;
	}

	private void LinkTableModelAttribute(DocumentDTO documentDTO, Model model) {
		if(tableDocument == null) {
			tableDocument = createLinkTable();
		}

		populateDocument = new DocumentDTOTablePopulators(documentDTO);
		
		tableDocument.setTablePopulator(populateDocument);	

		model.addAttribute("tableDocument", tableDocument);
	}


	public class DocumentDTOTablePopulators extends TablePopulator<DocumentDTO> {

		private HashMapFilter filter = new HashMapFilter();

		public DocumentDTOTablePopulators(DocumentDTO document){
			if(document != null) {
				if (document.getId() != null) {
					filter.addRestriction("id", new FilterProperty(
							SearchType.EQUALS, document.getId()));
				}

				if (StringUtils.isNotBlank(document.getAuthor())) {
					filter.addRestriction("author", new FilterProperty(
							SearchType.EQUALS, document.getAuthor()));
				}

				if (document.getLength() != null) {
					filter.addRestriction("length", new FilterProperty(
							SearchType.EQUALS, document.getLength()));
				}

				if (StringUtils.isNotBlank(document.getExtention())) {
					filter.addRestriction("extention", new FilterProperty(
							SearchType.EQUALS, document.getExtention()));
				}

				if (document.getCreationDate() != null) {
					filter.addRestriction("creationDate", new FilterProperty(
							SearchType.EQUALS, document.getCreationDate()));
				}
			}
		}

		@Override
		public Page<DocumentDTO> getPage(int offset, int pageSize, String sortColumnName, String sortOrder) {
			
			PageInfo p = new PageInfo(offset, pageSize, sortColumnName, sortOrder);
			return Page.createAsPage(fakeList, p, fakeList.size());
		}
	}
	
	private List<DocumentDTO> fakeList(){
		List<DocumentDTO> toReturn = new ArrayList<DocumentDTO>();

		try {
			toReturn.add(newDocumentDTO(1, "Zinedine Zidane", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(2, "Ronaldo", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(3, "Ronaldinho", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(4, "Lionel Messi", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(5, "Cristiano Ronaldo", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(6, "Romário", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(7, "George Weah", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(8, "Luís Figo", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(9, "Roberto Baggio", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(10, "Rivaldo", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(11, "Indiana \"Pistoleiro\" Jonas", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(12, "Mitro Golo", "txt", 34, dateFormater.parse("1991-12-12")));
			toReturn.add(newDocumentDTO(13, "Nuno Golos", "txt", 34, dateFormater.parse("1991-12-12")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	private DocumentDTO newDocumentDTO(Integer id, String author, String extention, Integer length, Date creationDate){
		DocumentDTO toReturn = new DocumentDTO();

		toReturn.setId(id);
		toReturn.setAuthor(author);
		toReturn.setExtention(extention);
		toReturn.setLength(length);
		toReturn.setCreationDate(new CustomDate(creationDate));

		return toReturn;
	}
}