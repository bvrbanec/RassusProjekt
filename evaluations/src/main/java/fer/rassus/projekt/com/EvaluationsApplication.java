package fer.rassus.projekt.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class EvaluationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationsApplication.class, args);
	}
}
