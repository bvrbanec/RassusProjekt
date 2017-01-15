package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/registrations/*")
public class WebRegistrationsController {
	
	@Autowired
	protected WebRegistrationsService registrationsService;
	@Autowired
	protected WebUsersServiceImpl usersService;
	
	public WebRegistrationsController(WebRegistrationsService registrationsService, WebUsersServiceImpl us) {
		this.registrationsService = registrationsService;
		this.usersService=us;
	}
	

	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getall(Model model) {
		List<Registration> registrations = new ArrayList<Registration>();
		registrations = registrationsService.getall();
		model.addAttribute("registrations", registrations);
		return "registrations-all";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("registration", new Registration());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createRegistration(Registration registration,Model model) {
		User us=new User();
		us.setEmail(registration.getEmail());
		us.setUsername(registration.getUsername());
		User usr=usersService.saveUser(us);
		registration.setPersonId(usr.getId());
		return registrationsService.register(registration,model);
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String checkLogin(Login login,HttpSession session, Model model) {
		
		Registration reg=registrationsService.login(login);
		if(reg==null)
		{
			model.addAttribute("loginErr", "Username or password incorrect");
			return "login";
		}
		//System.out.println("imam login "+reg.getUsername());
		session.setAttribute("loggedInUser", reg);
		//session.get
		//System.out.println(session.getAttribute("myName"));
		return "home";
		
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,Model model) {
		session.removeAttribute("loggedInUser");
		model.addAttribute("login", new Login());
		return "login";
	}
	

}
