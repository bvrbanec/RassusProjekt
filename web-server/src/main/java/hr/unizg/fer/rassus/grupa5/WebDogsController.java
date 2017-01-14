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
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getall(Model model) {
		List<Dog> registrations = new ArrayList<Dog>();
		registrations = dogsService.getall();
		model.addAttribute("dog", registrations);
		return "dogs-all";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("dog", new Dog());
		return "dog-registration";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createRegistration(Dog registration) {
		return dogsService.register(registration);
	}
	
	@RequestMapping("/DogNameById/{id}")
	public String byDogNamebyId(Model model, @PathVariable("id") Long Id){
		Dog dog = new Dog();
		dog = (Dog) dogsService.findById(Id);
		String dogName = dog.getName();
		model.addAttribute("dog", dogName);
		return "dogs-all";
		
	}

	@RequestMapping("/{id}")
	public String byDogId(Model model, @PathVariable("id") Long Id){
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findById(Id);
		model.addAttribute("dog", dogs);
		return "dogs-all";
		
	}
	
	@RequestMapping("owner/{owner}")
	public String byOwner(Model model,@PathVariable("owner") String dogOwner) {
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findByOwner(dogOwner);
		model.addAttribute("dog", dogs);
		return "dogs-all";
	}

}
