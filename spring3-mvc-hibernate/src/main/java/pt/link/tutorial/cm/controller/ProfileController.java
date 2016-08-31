package pt.link.tutorial.cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.link.libraries.linktables.ui.ColumnsProperties;
import pt.link.libraries.linktables.ui.LinkTable;
import pt.link.libraries.linktables.ui.TablePopulator;
import pt.link.libraries.pagging.Page;
import pt.link.libraries.pagging.PageInfo;
import pt.link.tutorial.cm.domain.Profile;
import pt.link.tutorial.cm.domain.User;
import pt.link.tutorial.cm.dto.ProfileDTO;
import pt.link.tutorial.cm.service.IProfileService;
import pt.link.tutorial.cm.service.IUserService;
import pt.link.tutorial.cm.validator.ProfileValidator;

@Controller
public class ProfileController extends CommonController{
	
	@Autowired
	protected IProfileService profileService;
	
	@Autowired
	protected IUserService userService;
	
	
	@InitBinder("profile")
	protected void initBinder(ServletRequestDataBinder binder) {
		binder.setValidator(new ProfileValidator());
	}
	
	@ModelAttribute("profileDTO")
	public ProfileDTO getProfileDto(){
		return new ProfileDTO();
	}
	
	@ModelAttribute("profile")
	public Profile getProfile(){
		return new Profile();
	}
	
	@RequestMapping(value = "/addOrUpdateProfile", method = RequestMethod.POST)
	public String addOrUpdateProfile(@ModelAttribute("profile") Profile profile, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("profile", profile);

			return "profileList";
		}

		profileService.addOrUpdateProfile(profile);

		return "redirect:/profiles.html";
	}
	
	@RequestMapping(value="/changeProfile", method = RequestMethod.POST)
	public String changeProfile(@ModelAttribute("profileDTO") ProfileDTO profileDTO,HttpServletRequest request, Model model){
		if(profileDTO != null){
			User user = userService.findByUsername(profileDTO.getUsername());
			for(Integer i : profileDTO.getprofileIDList())
				user.setProfile(String.valueOf(i));
			
			userService.addOrUpdateUser(user);
		}	
		
		return "redirect:/profiles.html";
	}
	
	@RequestMapping(value = "/getProfileDetail")
	public String getProfileDetail(@RequestParam("profileId") Integer profileID, ModelMap model) {
		Profile profile = profileService.getProfile(profileID);
		
		if (profile != null)
			model.put("profile", profile);
		
		return "profileList";
	}

	@RequestMapping(value= "/deleteProfile")
	public String deleteProfile(@RequestParam("profileID") Integer profileID) {
		profileService.removeProfile(profileID);

		return "redirect:/profiles.html";
	}
	
	@RequestMapping("/profiles")
	public String listProfiles(@RequestParam(value="username",required=false) String username, @Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO, BindingResult result, ModelMap model) {	
		
		LinkTable<Profile>profiles= new LinkTable<Profile>("tableProfiles");
		profiles.setColumnsNames(new String[]{"profile_id", "profile_name", "permissions_id"});
		profiles.addColumnProperties("profile_id", new ColumnsProperties("profile_id", "label.id"));		
		profiles.addColumnProperties("profile_name", new ColumnsProperties("profile_name", "label.name"));
		profiles.addColumnProperties("permissions_id", new ColumnsProperties("permissions_id", "label.permission"));
		profiles.setTablePopulator(new PerfisTablePopulator());
		profiles.setIdFieldName("profile_id");
		
		if(username!=null){
			profiles.setSelectedRows(getProfileID(username));
			profileDTO.setUsername(username);
			profileDTO.setId(Integer.parseInt(getProfileID(username)));
			model.addAttribute("profileDTO", profileDTO);
		}
		
		model.addAttribute("profileTable",profiles);
		return "profileList";
	}
	
	private String getProfileID(String username){
		User user = userService.findByUsername(username);	
		return user.getProfile();
	}
	
	public class PerfisTablePopulator extends TablePopulator<Profile>{
		
		public PerfisTablePopulator(){
			super();
		}

		@Override
		public Page<Profile> getPage(int offset, int pageSize, String sortColumnName, String sortOrder) {
			PageInfo pageInfo = new PageInfo(offset, pageSize, sortColumnName, sortOrder);
			
			List<Profile> profiles= profileService.listProfiles();
			
			return Page.createPageFromFullResults(profiles, pageInfo);
		}
		
	}
	
}
