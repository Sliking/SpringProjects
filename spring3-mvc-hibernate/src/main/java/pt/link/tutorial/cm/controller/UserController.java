package pt.link.tutorial.cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pt.link.libraries.linktables.ui.ColumnsProperties;
import pt.link.libraries.linktables.ui.LinkTable;
import pt.link.libraries.linktables.ui.TablePopulator;
import pt.link.libraries.pagging.Page;
import pt.link.libraries.pagging.PageInfo;
import pt.link.tutorial.cm.domain.User;
import pt.link.tutorial.cm.service.IUserService;
import pt.link.tutorial.cm.validator.UserValidator;

@Controller
public class UserController extends CommonController{

	@Autowired
	private IUserService userService;
	
	@InitBinder("userID")
	protected void initBinder(ServletRequestDataBinder binder) {
		binder.setValidator(new UserValidator());
	}
	
	@ModelAttribute("userID")
	public User getUser(){
		return new User();
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}
	
	@RequestMapping(value="/loginCredentials", method = RequestMethod.POST)
	public String loginCredentials(@ModelAttribute("userID") User user, BindingResult result, ModelMap model){
		return "redirect:/users.html";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	public String addOrUpdateUser(@Valid @ModelAttribute("userID") User user, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("userID", user);
			
			return "userList";
		}

		userService.addOrUpdateUser(user);

		return "redirect:/users.html";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("userID") User user, BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			model.put("userID", user);
			
			return "userList";
		}

		userService.addOrUpdateUser(user);

		return "redirect:/users.html";
	}
	
	@RequestMapping(value = "/getUserDetail")
	public String getUserDetail(@RequestParam("userId") String userID, ModelMap model) {
		User user = userService.findByUsername(userID);
		
		if (user != null)
			model.put("userID", user);
		
		return "userList";
	}

	@RequestMapping(value= "/deleteUser")
	public String deleteUser(@RequestParam("userID") String userID) {
		userService.removeUser(userID);

		return "redirect:/users.html";
	}
	
	@RequestMapping("/users")
	public String listUsers(ModelMap model) {
		
		LinkTable<User>users= new LinkTable<User>("userTable");
		users.setColumnsNames(new String[]{"username", "password", "enabled", "profile"});
		users.addColumnProperties("username", new ColumnsProperties("username", "label.username"));		
		users.addColumnProperties("password", new ColumnsProperties("password", "label.password"));
		users.addColumnProperties("enabled", new ColumnsProperties("enabled", "label.enabled"));
		users.addColumnProperties("profile", new ColumnsProperties("profile", "label.profile"));
		users.setTablePopulator(new UsersTablePopulator());
		users.setIdFieldName("username");
		model.addAttribute("userTable",users);

		return "userList";
	}	
	
	@RequestMapping(value="/changePermissions", method = RequestMethod.POST)
	public String changePermissions(@ModelAttribute("userID") User userID, ModelMap model) {
		return "redirect:/users.html&username=" + userID.getUsername();
	}
	
	public class UsersTablePopulator extends TablePopulator<User>{

		public UsersTablePopulator(){
			super();
		}
		
		@Override
		public Page<User> getPage(int offset, int pageSize, String sortColumnName, String sortOrder) {
			PageInfo pageInfo = new PageInfo(offset, pageSize, sortColumnName, sortOrder);
			
			List<User> users= userService.listUsers();
			
			return Page.createPageFromFullResults(users, pageInfo);
		}
		
	}
	
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}



	
}
