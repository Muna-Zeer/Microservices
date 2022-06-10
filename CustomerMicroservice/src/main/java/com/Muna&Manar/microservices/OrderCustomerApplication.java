package com.manar.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class OrderCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderCustomerApplication.class, args);
	}

}
