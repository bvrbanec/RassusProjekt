package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dogs/*")
public class WebDogsController {
	
	@Autowired
	protected WebDogsService dogsService;
	
	public WebDogsController(WebDogsService dogsService) {
		this.dogsService = dogsService;
	}

	@RequestMapping("/{dogId}")
	public String byDogId(Model model, @PathVariable("dogId") Long Id){
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findById(Id);
		model.addAttribute("dogs", dogs);
		return "dog-list";
		
	}
	
	@RequestMapping("/{dogOwner}")
	public String byOwner(Model model,@PathVariable("dogOwner") String dogOwner) {
		List<Dog> dogs = new ArrayList<Dog>();
		dogs = dogsService.findByOwner(dogOwner);
		model.addAttribute("dogs", dogs);
		return "dog-list";
	}
	
}
