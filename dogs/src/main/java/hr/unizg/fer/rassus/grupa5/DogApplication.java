package hr.unizg.fer.rassus.grupa5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.DB;
import com.mongodb.MongoClient;




@SpringBootApplication
@EnableAutoConfiguration
public class DogApplication extends SpringBootServletInitializer{


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DogApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DogApplication.class, args);
	}
}
