package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.domain.Profile;


public class ProfileValidator extends CommonController implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Profile.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((Profile) target);
		
		Profile profile = (Profile) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "profile.name.validator.error.empty");
	
		byte[] utf8Bytes;
		try {
			utf8Bytes = profile.getProfile_name().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("name", "profile.name.validator.error.tooBig");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("name", "profile.name.validator.error.wrongEncode");
		
		}
		
	}

}
