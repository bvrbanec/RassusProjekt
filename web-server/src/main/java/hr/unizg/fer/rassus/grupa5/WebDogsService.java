package hr.unizg.fer.rassus.grupa5;

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

	List<Dog> findById(Long dogId) {
		Dog[] dogs = null;
		dogs = restTemplate.getForObject(dogsServiceUrl + "/{dogId}", Dog[].class, dogId);
		if (dogs == null || dogs.length == 0)
			return null;
		else
			return Arrays.asList(dogs);
	}

	List<Dog> findByOwner(String dogOwner) {
		Dog[] dogs = null;
		dogs = restTemplate.getForObject(dogsServiceUrl + "/{dogOwner}", Dog[].class, dogOwner);
		if (dogs == null || dogs.length == 0)
			return null;
		else
			return Arrays.asList(dogs);
	}
	
}
