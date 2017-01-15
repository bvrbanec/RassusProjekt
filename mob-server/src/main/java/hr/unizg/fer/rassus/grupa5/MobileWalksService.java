package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MobileWalksService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String walksServiceUrl;

	public MobileWalksService(String walksServiceUrl) {
		this.walksServiceUrl = walksServiceUrl.startsWith("http") ? walksServiceUrl : "http://" + walksServiceUrl;
	}

	Walk findById(Long id) {
		return restTemplate.getForObject(walksServiceUrl + "/{id}", Walk.class, id);
	}
	
	List<Walk> findByDogId(Long dogId) {
		Walk[] walks = null;
		walks = restTemplate.getForObject(walksServiceUrl + "/dog/{dogId}", Walk[].class, dogId);
		if (walks == null || walks.length == 0)
			return null;
		else
			return Arrays.asList(walks);
	}

	List<Walk> findByWalkerId(Long walkerId) {
		Walk[] walks = null;
		walks = restTemplate.getForObject(walksServiceUrl + "/walker/{walkerId}", Walk[].class, walkerId);
		if (walks == null || walks.length == 0)
			return null;
		else
			return Arrays.asList(walks);
	}

	List<Walk> findByOwnerId(Long ownerId) {
		Walk[] walks = null;
		walks = restTemplate.getForObject(walksServiceUrl + "/owner/{ownerId}", Walk[].class, ownerId);
		if (walks == null || walks.length == 0)
			return null;
		else
			return Arrays.asList(walks);
	}

	List<Walk> findByWalkerIdIsNull() {
		Walk[] walks = null;
		List<Walk> listWalks = new ArrayList<Walk>();
		walks = restTemplate.getForObject(walksServiceUrl + "/active", Walk[].class);
		if (walks == null || walks.length == 0){
			System.out.println("nema dohvacenih setnji");
		}
		else{
			listWalks.addAll(Arrays.asList(walks));
		}
		return listWalks;
	}

	public Walk offerWalk(Walk walk) {
		return restTemplate.postForObject(walksServiceUrl + "/offer", walk, Walk.class);
	}

	public Walk acceptOffer(Walk walk) {
		return restTemplate.postForObject(walksServiceUrl + "/accept", walk, Walk.class);
	}
}
