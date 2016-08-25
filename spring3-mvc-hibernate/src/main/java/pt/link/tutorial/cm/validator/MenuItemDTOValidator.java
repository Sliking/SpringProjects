package pt.link.tutorial.cm.validator;

import java.io.UnsupportedEncodingException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pt.link.tutorial.cm.dto.MenuItemDTO;

public class MenuItemDTOValidator extends CommonController implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		//return Contact.class.isAssignableFrom(clazz);
		return MenuItemDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOG.warn((MenuItemDTO) target);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "label", "menuitem.label.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "href", "menuitem.href.validator.error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "menuitem.position.validator.error.empty");
		
		MenuItemDTO menuItem = (MenuItemDTO) target;
		
		byte[] utf8Bytes;
		try {
			utf8Bytes = menuItem.getLabel().getBytes("UTF-8");
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("label", "menuItem.label.validator.error.tooBig");
			}
			
			utf8Bytes = menuItem.getHref().getBytes("UTF-8");		
			
			if (utf8Bytes.length >= 255){
				errors.rejectValue("href", "menuItem.href.validator.error.tooBig");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			errors.rejectValue("label", "menuItem.label.validator.error.wrongEncode");
			errors.rejectValue("href", "menuItem.href.validator.error.wrongEncode");
		
		}	
	}

}
