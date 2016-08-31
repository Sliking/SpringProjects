package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.Contact;

public class ContactValidator extends CommonController implements Validator {
		
	@Override
	public boolean supports(Class<?> otherClass) {
		return Contact.class.equals(otherClass);
	}

	@Override
	public void validate(Object target, Errors e) {
		
		LOG.warn((Contact) target);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "firstname", "contact.firstname.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lastname", "contact.lastname.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "contact.email.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "telephone", "contact.telephone.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "birthDay", "contact.birthDay.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address", "contact.address.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "zipCode", "contact.zipCode.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "club", "contact.club.validator.error.empty");
		
		Contact contact = (Contact) target;
		
		Integer telephone = contact.getTelephone();
		
		if(telephone != null && telephone.intValue() <= 0){
			e.rejectValue("telephone", "typeMismatch.contact.telephone");
		}
		
		byte[] utf8Bytes;
		try {
			utf8Bytes = contact.getFirstname().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				e.rejectValue("firstname", "contact.firstname.validator.error.tooBig");
			}
			
			utf8Bytes = contact.getLastname().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				e.rejectValue("lastname", "contact.lastname.validator.error.tooBig");
			}
			
			utf8Bytes = contact.getEmail().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				e.rejectValue("email", "contact.email.validator.error.tooBig");
			}else if (!contact.getEmail().matches("\\S+@\\S+")) {
				e.rejectValue("email", "contact.email.validator.error.format");
			}
			
			utf8Bytes = contact.getZipCode().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				e.rejectValue("zipCode", "contact.zipCode.validator.error.tooBig");
			}else if (!contact.getZipCode().matches("^\\d+-+\\d+$")) {
				e.rejectValue("zipCode", "contact.zipCode.validator.error.format");
			}
			
			utf8Bytes = contact.getClub().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				e.rejectValue("club", "contact.club.validator.error.tooBig");
			}
			
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			
			e.rejectValue("firstname", "contact.firstname.validator.error.wrongEncode");
			e.rejectValue("lastname", "contact.lastname.validator.error.wrongEncode");
			e.rejectValue("email", "contact.email.validator.error.wrongEncode");
			e.rejectValue("zipCode", "contact.zipCode.validator.error.wrongEncode");
		}	
	}

}
