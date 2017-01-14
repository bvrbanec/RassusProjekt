package hr.unizg.fer.rassus.grupa5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	
	public String register(Registration reg,Model model) {
		restTemplate.postForObject(registrationsServiceUrl + "/create", reg, Registration.class);
		 model.addAttribute("login", new Login());
		return "login";
	}

	public Registration login(Login login) {
		Registration reg=restTemplate.postForObject(registrationsServiceUrl + "/login", login, Registration.class);
		return reg;
	}
	
	
	
	
	

}
