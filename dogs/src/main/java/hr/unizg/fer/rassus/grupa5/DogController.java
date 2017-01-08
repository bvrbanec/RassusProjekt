package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/dogs/*")
public class DogController {
	
	protected DogsRepository dogsRepository;
	
	@Autowired
	public DogController(DogsRepository dogsRepository) {
		super();
		this.dogsRepository = dogsRepository;
	}
	
	@RequestMapping("/dog/{Id}")
	public List<Dog> byId(@PathVariable("Id") Long Id) {
		return dogsRepository.findById(Id);
	}
	
	@RequestMapping("/owner/{dogOwner}")
	public List<Dog> byOwner(@PathVariable("dogOwner") String dogOwner) {
		return dogsRepository.findByOwner(dogOwner);
	}
	
	@RequestMapping("/breed/{dogBreed}")
	public List<Dog> byBreed(@PathVariable("dogBreed") String dogBreed) {
		return dogsRepository.findByBreed(dogBreed);
	}
	
	@RequestMapping("/gender/{dogGender}")
	public List<Dog> byGender(@PathVariable("dogGender") String dogGender) {
		return dogsRepository.findByGender(dogGender);
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	 public Iterable<Dog> getAllUsersReg(){
	    
	    try { 
	      return  dogsRepository.findAll();
	    }
	    catch (Exception ex) {
	      return null;
	    }
	  }
	
	

}
