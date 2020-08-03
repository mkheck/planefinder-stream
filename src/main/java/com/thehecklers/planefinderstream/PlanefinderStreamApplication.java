package com.thehecklers.planefinderstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PlanefinderStreamApplication {
	@Bean
	WebClient client() {
		return WebClient.create("http://192.168.1.193/ajax/aircraft");
	}

	public static void main(String[] args) {
		SpringApplication.run(PlanefinderStreamApplication.class, args);
	}

}
