package facebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import facebook.db.UserRepository;

@Controller
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	private UserRepository repository;
	
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public AuthenticationController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        
        User user = facebook.userOperations().getUserProfile();
        
        if(!verifyEmailAddress(user.getEmail())){
        	repository.save(new facebook.domain.User(user.getEmail().split("@")[0], user.getEmail()));
        }
        
        model.addAttribute("email", user.getEmail());
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "userinfo";
    }
    
    @GetMapping("/register")
    public String register(Model model){
    	model.addAttribute("userobject", new facebook.domain.User());
    	return "register";
    }
    
    @PostMapping("/registerform")
    public String registerPage(@ModelAttribute facebook.domain.User user){
    	saveUsers(user);
    	return "redirect:/connect/facebook";
    }
    
    @GetMapping("/login")
    public String login(Model model){
    	model.addAttribute("userlogin", new facebook.domain.User());
    	return "login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute facebook.domain.User user, Model model){
    	if(verifyEmailAddress(user.getEmail())){
//    		User faceuser = facebook.userOperations().getUserProfile();
//    		model.addAttribute("email", faceuser.getEmail());
//			model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
//			PagedList<Post> feed = facebook.feedOperations().getFeed();
//			model.addAttribute("feed", feed);
//			return "userinfo";
    		return "redirect:/connect/facebook";
    	}
    	else
    		return "home";
    }
    
    private void saveUsers(facebook.domain.User user){
    	repository.save(new facebook.domain.User(user.getEmail().split("@")[0], user.getEmail()));
    }
    
    private boolean verifyEmailAddress(String email){
    	if(repository.findByEmail(email).isEmpty())
    		return false;
    	return true;
    }

}