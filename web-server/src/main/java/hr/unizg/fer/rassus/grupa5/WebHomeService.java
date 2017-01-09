package hr.unizg.fer.rassus.grupa5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
@RequestMapping("/")
public class WebHomeService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String homeServiceUrl;

	public WebHomeService(String homeServiceUrl) {
		this.homeServiceUrl = homeServiceUrl.startsWith("http") ? homeServiceUrl : "http://" + homeServiceUrl;
	}
}
