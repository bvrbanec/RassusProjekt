package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

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
	
	public WebDogsController(WebDogsService dogsService) {
		this.dogsService = dogsService;
	}
	
	@RequestMapping(value = "/allDogs", method = RequestMethod.GET)
	public String getall(Model model) {
		List<Dog> registrations = new ArrayList<Dog>();
		registrations = dogsService.getall();
		model.addAttribute("dogList", registrations);
		return "dogs-all";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("dogsList", new Dog());
		return "dog-list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createRegistration(Dog registration) {
		return dogsService.register(registration);
	}
	
	@RequestMapping("/{DogNameById}")
	public String byDogNamebyId(Model model, @PathVariable("dogId") Long Id){
		Dog dog = new Dog();
		dog = (Dog) dogsService.findById(Id);
		String dogName = dog.getName();
		model.addAttribute("dogList", dogName);
		return "dog-list";
		
	}

	@RequestMapping("/{dogId}")
	public String byDogId(Model model, @PathVariable("dogId") Long Id){
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findById(Id);
		model.addAttribute("dogList", dogs);
		return "dog-list";
		
	}
	
	@RequestMapping("/{dogOwner}")
	public String byOwner(Model model,@PathVariable("dogOwner") String dogOwner) {
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findByOwner(dogOwner);
		model.addAttribute("dogList", dogs);
		return "dog-list";
	}
	
}
