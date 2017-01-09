package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

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

	public WebRegistrationsController(WebRegistrationsService registrationsService) {
		this.registrationsService = registrationsService;
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
	public String createRegistration(Registration registration) {
		return registrationsService.register(registration);
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String checkLogin(Login login) {
		System.out.println("imam login");
		return registrationsService.login(login);
	}

	
	/*
	@RequestMapping("/walker/{walkerId}")
	public String byWalker(Model model, @PathVariable("walkerId") Long walkerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(walkerId);
		model.addAttribute("walkerWalks", walks);
		return "walker-walks";
	}

	@RequestMapping("/owner/{ownerId}")
	public String byOwner(Model model, @PathVariable("ownerId") Long ownerId) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerId(ownerId);
		model.addAttribute("ownerWalks", walks);
		return "owner-walks";
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeWalks(Model model) {
		List<Walk> walks = new ArrayList<Walk>();
		walks = walksService.findByWalkerIdIsNull();
		System.out.println("setnje");
		System.out.println(walks.get(0).getPrice());
		model.addAttribute("activeWalks", walks);
		return "active-walks";
	}
	*/
/*
	@RequestMapping(value = "/offer", method = RequestMethod.POST)
	public Walk offerWalk(@RequestBody Walk walk) {
		return walksService.offerWalk(walk);
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Walk acceptOffer(@RequestBody Walk walk) {
		return walksService.acceptOffer(walk);
	}
*/
}
