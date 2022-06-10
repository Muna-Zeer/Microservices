package com.manar.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class ProdustsMicroservices1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProdustsMicroservices1Application.class, args);
	}

}
