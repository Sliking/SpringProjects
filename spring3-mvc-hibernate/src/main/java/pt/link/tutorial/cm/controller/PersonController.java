package pt.link.tutorial.cm.controller;

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

import pt.link.tutorial.cm.domain.Person;
import pt.link.tutorial.cm.service.IPersonService;
import pt.link.tutorial.cm.validator.PersonValidator;

@Controller
public class PersonController extends CommonController{
		
	@Autowired
	protected IPersonService personService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new PersonValidator());
	}
	
	@RequestMapping(value = "/addOrUpdatePerson", method = RequestMethod.POST)
	public String addOrUpdatePerson(@Valid @ModelAttribute("person") Person person, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("person", person);
			
			populateModel(model);
			
			return "personList";
		}

		personService.addOrUpdatePerson(person);

		return "redirect:/persons.html";
	}
	
	@RequestMapping(value = "/getPersonDetail")
	public String getPersonDetail(@RequestParam("personId") Integer personID, ModelMap model) {
		Person person = personService.getPerson(personID);
		
		if (person != null)
			model.put("person", person);
		
		populateModel(model);
		
		return "personList";
	}

	@RequestMapping(value= "/deletePerson")
	public String deletePerson(@RequestParam("personID") Integer personID) {
		personService.removePerson(personID);

		return "redirect:/persons.html";
	}
	
	@RequestMapping("/persons")
	public String listPersons(ModelMap model) {
		Person person = new Person();
		
		model.put("person", person);
		
		populateModel(model);
		
		return "personList";
	}
	
	protected void populateModel(ModelMap model) {
		model.put("personList", personService.listPersons());
	}

}
