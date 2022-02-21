package io.javabrains.ratingsdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingsdataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsdataserviceApplication.class, args);
	}

}
