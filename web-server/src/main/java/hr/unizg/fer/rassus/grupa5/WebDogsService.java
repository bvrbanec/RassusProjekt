package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebDogsService {
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String dogsServiceUrl;

	public WebDogsService(String dogsServiceUrl) {
		this.dogsServiceUrl = dogsServiceUrl.startsWith("http") ? dogsServiceUrl : "http://" + dogsServiceUrl;
	}
	
	List<Dog> getall() {

		Dog[] regs = null;
		List<Dog> listDogRegistrations = new ArrayList<Dog>();
		regs = restTemplate.getForObject(dogsServiceUrl + "/all", Dog[].class);
		System.out.println("all dogs: "+ regs);
		if (regs == null || regs.length == 0){
			System.out.println("no dogs registered");
			Dog emptyreg = new Dog();
			listDogRegistrations.add(emptyreg);
		}
		else{
			listDogRegistrations.addAll(Arrays.asList(regs));
		}
		return listDogRegistrations;
	}
	
	
	public Dog register(Dog registration) {
		return restTemplate.postForObject(dogsServiceUrl + "/create", registration, Dog.class);
	}
	
	/*String findNameById(Long id){
		Dog dog = null;
		dog = restTemplate.getForObject(dogsServiceUrl + "/nameById/{id}", Dog.class, id);
		String dogName = dog.getName();
		return dogName;
	}*/

	Dog findById(Long id) {
		Dog dog = null;
		dog = restTemplate.getForObject(dogsServiceUrl + "/{id}", Dog.class, id);
		if (dog == null)
			return null;
		else
			return dog;
	}

	List<Dog> findByOwnerId(Long ownerId) {
		Dog[] dogs = null;
		dogs = restTemplate.getForObject(dogsServiceUrl + "/owner/{owner}", Dog[].class, ownerId);
		if (dogs == null || dogs.length == 0)
			return null;
		else
			return Arrays.asList(dogs);
	}
	
}
