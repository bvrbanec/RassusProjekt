package hr.unizg.fer.rassus.grupa5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/nameById/{id}")
	public String NamebyId(@PathVariable("id") Long Id){
		return dogsRepository.findNameById(Id);
	}
	
	@RequestMapping("/{id}")
	public List<Dog> byId(@PathVariable("id") Long Id) {
		return dogsRepository.findById(Id);
	}
	
	@RequestMapping("/owner/{owner}")
	public List<Dog> byOwner(@PathVariable("owner") String dogOwner) {
		return dogsRepository.findByOwner(dogOwner);
	}
	
	@RequestMapping("/breed/{breed}")
	public List<Dog> byBreed(@PathVariable("breed") String dogBreed) {
		return dogsRepository.findByBreed(dogBreed);
	}
	
	@RequestMapping("/gender/{gender}")
	public List<Dog> byGender(@PathVariable("gender") String dogGender) {
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
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	 public Dog createRegistration(@RequestBody Dog dog){
	    
	    Dog reg;
	    try { 
	      reg = dogsRepository.save(new Dog(dog));
	    }
	    catch (Exception ex) {
	      return null;
	    }
	    return reg;
	  }
	
	

}
