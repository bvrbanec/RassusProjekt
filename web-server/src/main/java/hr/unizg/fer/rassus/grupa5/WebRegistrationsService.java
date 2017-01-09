package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class WebRegistrationsService {
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String registrationsServiceUrl;

	public WebRegistrationsService(String registrationsServiceUrl) {
		this.registrationsServiceUrl = registrationsServiceUrl.startsWith("http") ? registrationsServiceUrl : "http://" + registrationsServiceUrl;
	}

	List<Registration> getall() {

		Registration[] regs = null;
		List<Registration> listRegistrations = new ArrayList<Registration>();
		regs = restTemplate.getForObject(registrationsServiceUrl + "/all", Registration[].class);
		System.out.println("sve reg"+regs);
		if (regs == null || regs.length == 0){
			System.out.println("nema dohvacenih registr");
			Registration emptyreg = new Registration();
			listRegistrations.add(emptyreg);
		}
		else{
			listRegistrations.addAll(Arrays.asList(regs));
			//listRegistrations.add(regs);
		}
		return listRegistrations;
	}
	
	
	public String register(Registration reg) {
		restTemplate.postForObject(registrationsServiceUrl + "/create", reg, Registration.class);
		return "login";
	}

	public String login(Login login) {
		Login log=restTemplate.postForObject(registrationsServiceUrl + "/login", login, Login.class);
		if (log==null)
			return "login";
		else
			return "succesfullogin";
	}
	
	
	
	
	
	
	/*
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
			Walk emptyWalk = new Walk();
			listWalks.add(emptyWalk);
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
	*/
}
