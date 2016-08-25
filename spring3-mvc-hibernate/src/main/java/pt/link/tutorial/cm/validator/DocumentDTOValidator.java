package pt.link.tutorial.cm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.dto.DocumentDTO;

public class DocumentDTOValidator extends CommonController implements Validator{

	@Override
	public boolean supports(Class<?> otherClass) {
		return DocumentDTO.class.equals(otherClass);
	}

	@Override
	public void validate(Object target, Errors e) {
		LOG.warn((DocumentDTO) target);
	}
}

