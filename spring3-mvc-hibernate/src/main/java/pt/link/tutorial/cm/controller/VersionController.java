package pt.link.tutorial.cm.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VersionController extends CommonController{
	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String showVersion(Model model, Principal principal, HttpServletRequest request, HttpSession session) {
		return "version";
	}
}