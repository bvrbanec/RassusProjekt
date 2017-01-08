package hr.unizg.fer.rassus.grupa5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class WebServerApplication {

	public static final String WALKS_SERVICE_URL = "http://WALKS-SERVICE/walks";
	public static final String DOGS_SERVICE_URL = "http://DOGS-SERVICE/dogs";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton"
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The WebWalksService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebWalksService walksService() {
		return new WebWalksService(WALKS_SERVICE_URL);
	}
	
	@Bean
	public WebDogsService dogsService() {
		return new WebDogsService(DOGS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebWalksService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebWalksController walksController() {
		return new WebWalksController(walksService());
	}
	
	@Bean
	public WebDogsController dogsController() {
		return new WebDogsController(dogsService());
	}

}
