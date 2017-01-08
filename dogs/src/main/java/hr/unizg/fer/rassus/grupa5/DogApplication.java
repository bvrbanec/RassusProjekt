package hr.unizg.fer.rassus.grupa5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient

public class DogApplication{

	public static void main(String[] args) {
		SpringApplication.run(DogApplication.class, args);
	}
}
