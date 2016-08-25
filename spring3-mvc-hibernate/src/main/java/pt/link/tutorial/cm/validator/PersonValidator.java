package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.Person;

public class PersonValidator extends CommonController implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((Person) target);
		
		Person person = (Person) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "person.firstname.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "person.lastname.validator.error.empty");
	
		byte[] utf8Bytes;
		try {
			utf8Bytes = person.getFirstname().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("firstname", "person.firstname.validator.error.tooBig");
			}
			
			utf8Bytes = person.getLastname().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("lastname", "person.lastname.validator.error.tooBig");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("firstname", "person.firstname.validator.error.wrongEncode");
			errors.rejectValue("lastname", "person.lastname.validator.error.wrongEncode");
		
		}		
		

	}

}
