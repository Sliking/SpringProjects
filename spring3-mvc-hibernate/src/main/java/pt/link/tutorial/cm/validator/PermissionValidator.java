package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.Permission;

public class PermissionValidator extends CommonController implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Permission.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((Permission) target);
		
		Permission permission = (Permission) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "permission.name.validator.error.empty");
	
		byte[] utf8Bytes;
		try {
			utf8Bytes = permission.getName().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("name", "permission.name.validator.error.tooBig");
			}
			
			utf8Bytes = permission.getName().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("name", "permission.name.validator.error.tooBig");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("name", "permission.name.validator.error.wrongEncode");
		
		}	
		
	}

}
