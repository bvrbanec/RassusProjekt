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
public class MobServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobServerApplication.class, args);
	}
	public static final String WALKS_SERVICE_URL = "http://WALKS-SERVICE/walks";

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The MobileWalksService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public MobileWalksService walksService() {
		return new MobileWalksService(WALKS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link MobileWalksService} to use.
	 * 
	 * @return
	 */
	@Bean
	public MobileWalksController walksController() {
		return new MobileWalksController(walksService());
	}

}
