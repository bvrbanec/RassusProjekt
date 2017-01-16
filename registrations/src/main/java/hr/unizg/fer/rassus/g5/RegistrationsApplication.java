package hr.unizg.fer.rassus.g5;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;



@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient

public class RegistrationsApplication {
	/*
	@Bean
	CommandLineRunner commandLineRunner(RegistrationRepository rr){
		return strings->{
			Stream.of("Pero","Krafna").forEach(n->rr.save(new Registration(n)));
		};
	}*/
	public static void main(String[] args) {
		SpringApplication.run(RegistrationsApplication.class, args);
	}
}
