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

import pt.link.tutorial.cm.domain.Club;
import pt.link.tutorial.cm.service.IClubService;
import pt.link.tutorial.cm.validator.ClubValidator;

@Controller
public class ClubController extends CommonController{
	
	@Autowired
	protected IClubService clubService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ClubValidator());
	}
	
	@RequestMapping(value = "/addOrUpdateClub", method = RequestMethod.POST)
	public String addOrUpdateClub(@Valid @ModelAttribute("club") Club club, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("club", club);
			
			populateModel(model);
			
			return "clubList";
		}

		clubService.addOrUpdateClub(club);

		return "redirect:/clubs.html";
	}
	
	@RequestMapping(value = "/getClubDetail")
	public String getClubDetail(@RequestParam("clubId") Integer clubID, ModelMap model) {
		Club club = clubService.getClub(clubID);
		
		if (club != null)
			model.put("club", club);
		
		populateModel(model);
		
		return "clubList";
	}

	@RequestMapping(value= "/deleteClub")
	public String deleteClub(@RequestParam("clubID") Integer clubID) {
		clubService.removeClub(clubID);

		return "redirect:/clubs.html";
	}
	
	@RequestMapping("/clubs")
	public String listClubs(ModelMap model) {
		Club club = new Club();
		
		model.put("club", club);
		
		populateModel(model);
		
		return "clubList";
	}
	
	protected void populateModel(ModelMap model) {
		model.put("clubList", clubService.listClubs());
	}
}
