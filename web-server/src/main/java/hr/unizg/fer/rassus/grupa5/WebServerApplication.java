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
	public static final String HOME_SERVICE_URL = "http://";
	public static final String REGISTRATIONS_SERVICE_URL = "http://REGISTRATIONS-SERVICE/registrations";
	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE/users";
	public static final String EVALUATIONS_SERVICE_URL = "http://EVALUATIONS-SERVICE/evaluations";

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
	
	@Bean
	public WebRegistrationsService registrationsService() {
		return new WebRegistrationsService(REGISTRATIONS_SERVICE_URL);
	}
	
	@Bean
	public WebHomeService homeService() {
		return new WebHomeService(HOME_SERVICE_URL);
	}
	
	@Bean
	public WebUsersServiceImpl usersService() {
		return new WebUsersServiceImpl(USERS_SERVICE_URL);
	}

	@Bean
	public WebEvaluationsService evService() {
		return new WebEvaluationsService(EVALUATIONS_SERVICE_URL);
	}
	/**
	 * Create the controller, passing it the {@link WebWalksService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebWalksController walksController() {
		return new WebWalksController(walksService(), dogsService(), usersService(), evService());
	}
	
	@Bean
	public WebDogsController dogsController() {
		return new WebDogsController(dogsService(), evService(), usersService());
	}
	
	@Bean
	public WebRegistrationsController registrationsController() {
		return new WebRegistrationsController(registrationsService(),usersService());
	}
	
	@Bean
	public WebHomeController homeController() {
		return new WebHomeController(homeService());
	}
	
	@Bean
	public WebUsersController usersController() {
		return new WebUsersController(usersService());
	}
	@Bean
	public WebEvaluationsController evsController() {
		return new WebEvaluationsController(evService());
	}

}
