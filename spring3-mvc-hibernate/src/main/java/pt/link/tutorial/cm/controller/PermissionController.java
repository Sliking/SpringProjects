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
import pt.link.tutorial.cm.domain.Permission;
import pt.link.tutorial.cm.domain.Profile;
import pt.link.tutorial.cm.dto.PermissionDTO;
import pt.link.tutorial.cm.service.IPermissionService;
import pt.link.tutorial.cm.service.IProfileService;
import pt.link.tutorial.cm.validator.PermissionValidator;

@Controller
public class PermissionController extends CommonController{

	@Autowired
	protected IPermissionService permissionService;
	
	@Autowired
	protected IProfileService profileService;
	
	@InitBinder("permission")
	protected void initBinder(ServletRequestDataBinder binder) {
		binder.setValidator(new PermissionValidator());
	}
	
	@ModelAttribute("permissionDTO")
	public PermissionDTO getPermissionDto(){
		return new PermissionDTO();
	}
	
	@ModelAttribute("permission")
	public Permission getPermission(){
		return new Permission();
	}
	
	@RequestMapping(value = "/addOrUpdatePermission", method = RequestMethod.POST)
	public String addOrUpdatePermission(@ModelAttribute("permission") Permission permission, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("permission", permission);

			return "permissionList";
		}

		permissionService.addOrUpdatePermission(permission);

		return "redirect:/permission.html";
	}
	
	@RequestMapping(value="/changePermission", method = RequestMethod.POST)
	public String changePermission(@ModelAttribute("permissionDTO") PermissionDTO permissionDTO,HttpServletRequest request, Model model){
		if(permissionDTO != null){
			Profile profile = profileService.getProfile(permissionDTO.getProfileID());
			profile.setPermissions_id(convertListToString(permissionDTO.getPermissionIDList()));
			profileService.addOrUpdateProfile(profile);
		}	
		
		return "redirect:/permissions.html";
	}
	
	private String convertListToString(List<Integer> list){
		String toReturn = "";
		for(Integer i : list){
			toReturn += i + ",";
		}
		toReturn = toReturn.substring(0, toReturn.length()-1);
		return toReturn;
	}
	
	@RequestMapping("/permissions")
	public String listPermissions(@RequestParam(value="id",required=false) Integer id, @Valid @ModelAttribute("permissionDTO") PermissionDTO permissionDTO, BindingResult result, ModelMap model) {	
		
		System.out.println("----->Id: " + id);
		
		LinkTable<Permission> permissions = new LinkTable<Permission>("tablePermissions");
		permissions.setColumnsNames(new String[]{"id", "name"});
		permissions.addColumnProperties("id", new ColumnsProperties("id", "label.id"));		
		permissions.addColumnProperties("name", new ColumnsProperties("name", "label.name"));		
		permissions.setTablePopulator(new PermissionsTablePopulator());
		permissions.setIdFieldName("id");
		
		if(id!=null){
			permissions.setSelectedRows(getPermissionsRows(id));
			permissionDTO.setProfileID(id);
			model.addAttribute("permissionDTO", permissionDTO);
		}
		
		model.addAttribute("permissionTable",permissions);
		return "permissionList";
	}
	
	private String getPermissionsRows(Integer id){
		Profile profile = profileService.getProfile(id);
		return profile.getPermissions_id();
	}
	
	public class PermissionsTablePopulator extends TablePopulator<Permission>{
		
		public PermissionsTablePopulator(){
			super();
		}

		@Override
		public Page<Permission> getPage(int offset, int pageSize, String sortColumnName, String sortOrder) {
			PageInfo pageInfo = new PageInfo(offset, pageSize, sortColumnName, sortOrder);
			
			List<Permission> permission= permissionService.listPermissions();
			
			return Page.createPageFromFullResults(permission, pageInfo);
		}
		
	}

}
