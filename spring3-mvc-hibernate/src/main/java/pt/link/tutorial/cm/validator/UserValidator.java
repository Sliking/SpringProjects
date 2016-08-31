package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.User;

public class UserValidator extends CommonController implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((User) target);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "user.name.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.validator.error.empty");
	
		User user = (User) target;
		
		byte[] utf8Bytes;
		try {
			utf8Bytes = user.getUsername().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("name", "user.name.validator.error.tooBig");
			}
			
			utf8Bytes = user.getPassword().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("password", "user.password.validator.error.tooBig");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("name", "user.name.validator.error.wrongEncode");
			errors.rejectValue("password", "user.password.validator.error.wrongEncode");
		
		}	
	
	
	}

}
