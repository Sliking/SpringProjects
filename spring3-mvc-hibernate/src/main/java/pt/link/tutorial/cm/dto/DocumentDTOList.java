package pt.link.tutorial.cm.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentDTOList {
    
	private List<DocumentDTO> documentsDTO = new ArrayList<DocumentDTO>();

	public List<DocumentDTO> getDocumentsDTO() {
        return documentsDTO;
    }
 
    public void setContacts(List<DocumentDTO> documentsDTO) {
        this.documentsDTO = documentsDTO;
    }
}
