package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.Club;

public class ClubValidator extends CommonController implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Club.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((Club) target);
		
		Club club = (Club) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "club.name.validator.error.empty");
	
		byte[] utf8Bytes;
		try {
			utf8Bytes = club.getName().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("name", "club.name.validator.error.tooBig");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("name", "club.name.validator.error.wrongEncode");
		
		}
		
	}

}
