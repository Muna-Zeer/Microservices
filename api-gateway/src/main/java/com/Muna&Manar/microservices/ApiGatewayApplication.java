package com.manar.microservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World")
								.addRequestParameter("FirstName", "Rawan")
								.addRequestParameter("LastName", "Gedeon"))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/payments/**")
			            .filters(f -> f.circuitBreaker(config -> config
			                .setName("mycmd")
			                .setFallbackUri("forward:/fallback")))
			            .uri("lb://PaymentMicrservices"))
				
				.route(p -> p
						.path("/customers/**")
						.uri("lb://CustomersMicrservices"))
				.route(p -> p
						.path("/products/**")
						.uri("lb://ProdustsMicrservices"))
				.route(p -> p
						.path("/productLines/**")
						.uri("lb://ProdustsMicrservices"))
				.route(p -> p
						.path("/patments/**")
						.uri("lb://PaymentMicrservices"))
				.route(p -> p
						.path("/orders/**")
						.uri("lb://OrderDetailsMicrservices"))
				.route(p -> p
						.path("/orderDetails/**")
						.uri("lb://OrderDetailsMicrservices"))
				.build();
	}
	
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
	  return Mono.just("A problem has occured, please try again later.");
	}
	
	

}

