package pt.link.security.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page - Admin Page!");
		model.setViewName("admin");

		return model;

	}
	
	@RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		if(isRememberMeAuthenticated()){
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("/login");
		}
		
		else
			model.setViewName("update");
		
		return model;
	}

	@RequestMapping(value = "/dba**", method = RequestMethod.GET)
	public ModelAndView dbaPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page - Database Page!");
		model.setViewName("admin");

		return model;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		System.out.println("---->Username: " + name);
		
	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
		String targetUrl = getRememberMeTargetUrlFromSession(request);
		System.out.println(targetUrl);
		if(StringUtils.hasText(targetUrl)){
			model.addObject("targetUrl", targetUrl);
			model.addObject("loginUpdate", true);
		}
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}
	
	private boolean isRememberMeAuthenticated(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return false;
		else
			return RememberMeAuthenticationToken.class.isAssignableFrom(auth.getClass());
	}
	
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		 /*The boolean value is necessary in case there is no current session. 
		  * It won't be created one when it is set to false
		  */
		HttpSession session = request.getSession(false);
		
		if(session != null)
			session.setAttribute("targetUrl", "/admin/update");	
	}
	
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null)
			return session.getAttribute("targetUrl") == null ? "" : session.getAttribute("targetUrl").toString();
		return "";
	}
}
