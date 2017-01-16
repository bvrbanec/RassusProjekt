package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dogs/*")
public class WebDogsController {
	
	@Autowired
	protected WebDogsService dogsService;
	protected WebEvaluationsService evalService;
	protected WebUsersService usersService;
	
	public WebDogsController(WebDogsService dogsService, WebEvaluationsService evalService, WebUsersService usersService) {
		this.dogsService = dogsService;
		this.evalService = evalService;
		this.usersService = usersService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getall(Model model) {
		List<Dog> registrations = new ArrayList<Dog>();
		registrations = dogsService.getall();
		model.addAttribute("dog", registrations);
		return "dogs-all";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("dog",new Dog());
		return "dog-registration";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createRegistration(Dog registration, HttpSession session) {
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long ownerId = reg.getPersonId();
		registration.setOwnerId(ownerId);
		return dogsService.register(registration);
	}
	
	/*@RequestMapping("/nameById/{id}")
	public String byDogNamebyId(Model model, @PathVariable("id") Long Id){
		Dog dog = new Dog();
		dog = (Dog) dogsService.findById(Id);
		String dogName = dog.getName();
		model.addAttribute("dog", dogName);
		return "dogs-all";
		
	}*/

	@RequestMapping("{id}")
	public String byDogId(Model model, @PathVariable("id") Long Id, HttpSession session){
		Dog dog = new Dog();
		dog = dogsService.findById(Id);
		model.addAttribute("dog", dog);
		Registration reg = (Registration) session.getAttribute("loggedInUser");
		Long walkerId = reg.getPersonId();
		Evaluation eval = evalService.checkExisting(walkerId, Id);
		if (eval != null){ 
			model.addAttribute("canRate", true);
			model.addAttribute("dogEvaluation", new Evaluation());
		}
		List<Evaluation> evals = evalService.findByDogId(Id);
		for(Evaluation e : evals) {
			User walker = usersService.findById(e.getWalkerId());
			e.setWalkerName(walker.getUsername());
		}
		model.addAttribute("evaluations", evals);
		
		return "dog-profile";
		
	}
	
	@RequestMapping("owner/{ownerId}")
	public String byOwner(Model model,@PathVariable("ownerId") Long dogOwner) {
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findByOwnerId(dogOwner);
		model.addAttribute("ownerDogs", dogs);
		return "ownerId-dogs";
	}

}
