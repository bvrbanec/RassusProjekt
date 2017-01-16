package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import scala.annotation.meta.setter;

@Service
public class WebEvaluationsService {
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String evalsServiceUrl;

	public WebEvaluationsService(String evalsServiceUrl) {
		this.evalsServiceUrl = evalsServiceUrl.startsWith("http") ? evalsServiceUrl : "http://" + evalsServiceUrl;
	}

	List<Evaluation> findAll() {
		Evaluation[] evals = null;
		List<Evaluation> listEvals = new ArrayList<Evaluation>();
		evals = restTemplate.getForObject(evalsServiceUrl + "/all", Evaluation[].class);
		System.out.println("sve ev" + evals);
		if (evals == null || evals.length == 0) {
			System.out.println("nema dohvacenih ev");
			Evaluation emptyreg = new Evaluation();
			listEvals.add(emptyreg);
		} else {
			listEvals.addAll(Arrays.asList(evals));
		}
		return listEvals;
	}

	List<Evaluation> findByOwnerId(Long ownerId) {
		Evaluation[] ev = null;
		ev = restTemplate.getForObject(evalsServiceUrl + "/owner/{ownerId}", Evaluation[].class, ownerId);
		if (ev == null || ev.length == 0)
			return null;
		else
			return Arrays.asList(ev);
	}

	List<Evaluation> findByWalkerId(Long walkerId) {
		Evaluation[] ev = null;
		ev = restTemplate.getForObject(evalsServiceUrl + "/walker/{walkerId}", Evaluation[].class, walkerId);
		if (ev == null || ev.length == 0)
			return null;
		else
			return Arrays.asList(ev);
	}

	Evaluation checkExisting(Long walkerId, Long dogId) {
		Evaluation evaluation = new Evaluation();
		evaluation.setDogId(dogId);
		evaluation.setWalkerId(walkerId);
		Evaluation ev = restTemplate.postForObject(evalsServiceUrl + "/check", evaluation, Evaluation.class);
		return ev;
	}
	
	List<Evaluation> findByDogId(Long dogId) {
		Evaluation[] ev = null;
		ev = restTemplate.getForObject(evalsServiceUrl + "/dog/{dogId}", Evaluation[].class, dogId);
		if (ev == null || ev.length == 0)
			return null;
		else
			return Arrays.asList(ev);
	}
	
	public Evaluation save(Evaluation evaluation) {
		return restTemplate.postForObject(evalsServiceUrl + "/save", evaluation, Evaluation.class);
	}

}
