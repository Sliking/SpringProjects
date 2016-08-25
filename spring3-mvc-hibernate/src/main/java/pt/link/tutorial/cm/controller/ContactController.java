package pt.link.tutorial.cm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.link.tutorial.cm.domain.Contact;
import pt.link.tutorial.cm.service.IContactService;
import pt.link.tutorial.cm.validator.ContactValidator;

@Controller
public class ContactController extends CommonController{
	
	
	@Autowired
	private IContactService contactService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ContactValidator());
	}
	
	
	private enum ListOrder {
		FIRSTNAME("firstName"),
		LATSTNAME("lastName"),
		BIRTH_DAY("birthDay"),
		EMAIL("email"),
		TELEPHONE("telephone"),
		ADDRESS("address"),
		ZIP_CODE("zipCode"),
		CLUB("club");
		
		private static ListOrder DEFAULT_VALUE = FIRSTNAME;
		
		public static final String ASC = "asc";
		public static final String DESC = "desc";

		private String value;
		private String orderWay;
		
		ListOrder(String value) {
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
		
		public String getOrderWay(){
			if(orderWay == null) {
				return ASC;
			}else {
				return orderWay;
			}
		}
		
		public void changeOrderWay(){
			
			if(orderWay == ASC) {
				orderWay = DESC;
			}else {
				orderWay = ASC;
			}
		}
		
		public static ListOrder getEnumByValue(String value){
			ListOrder toReturn = DEFAULT_VALUE;
			
			if(value != null && !value.isEmpty()){
				for(ListOrder enumElement : ListOrder.values()) {
					if(value.equalsIgnoreCase(enumElement.getValue())){
						toReturn = enumElement;
						break;
					}
				}
			}
			
			return toReturn;
		}
	}
	
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	public String addOrUpdateContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("contact", contact);
			
			populateModel(model);
			
			return "contactList";
		}

		contactService.addOrUpdateContact(contact);

		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/getDetail")
	public String getContactDetail(@RequestParam("contactId") Integer contactId, ModelMap model) {
		Contact contact = contactService.getContact(contactId);
		
		if (contact != null)
			model.put("contact", contact);
		
		populateModel(model);
		
		return "contactList";
	}

	@RequestMapping("/delete")
	public String deleteContact(@RequestParam("contactId") Integer contactId) {
		contactService.removeContact(contactId);

		return "redirect:/index.html";
	}
	
	@RequestMapping("/index")
	public String listContacts(ModelMap model, @RequestParam(value="order", required = false) String order) {
		model.put("contact", new Contact());
		
		ListOrder orderEnum = ListOrder.getEnumByValue(order);
		
		List<Contact> contactsList = null;
		
		String orderWay = orderEnum.getOrderWay();
		
		switch(orderEnum) {
			case FIRSTNAME:
				
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByFirstNameAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByFirstNameDesc();
				}
				break;
			case LATSTNAME:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByLastNameAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByLastNameDesc();
				}
				break;
			case BIRTH_DAY:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderBirthDayAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderBirthDayDesc();
				}
				break;
			case EMAIL:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByEmailAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByEmailDesc();
				}
				break;
			case TELEPHONE:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByTelephoneAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByTelephoneDesc();
				}
				break;
			case ADDRESS:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByAddressAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByAddressDesc();
				}
				break;
			case ZIP_CODE:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByZipCodeAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByZipCodeDesc();
				}
				break;
			case CLUB:
				if(orderWay == ListOrder.ASC) {
					contactsList = contactService.listContactsOrderByClubAsc();
				}else if(orderWay == ListOrder.DESC) {
					contactsList = contactService.listContactsOrderByClubDesc();
				}
				break;
			default:
				contactsList = contactService.listContacts();
		}
		
		orderEnum.changeOrderWay();
		
		model.put("orderEnum", orderEnum.getValue());
		model.put("orderWay", orderWay);
		
		model.put("contactList", contactsList);
		
		return "contactList";
	}

	protected void populateModel(ModelMap model) {
		model.put("contactList", contactService.listContacts());
	}

	/**
	 * @return the contactService
	 */
	public IContactService getContactService() {
		return contactService;
	}

	/**
	 * @param contactService the contactService to set
	 */
	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}
	
}
