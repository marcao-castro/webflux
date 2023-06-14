package com.apirest.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.scheduler.ReactorBlockHoundIntegration;

@SpringBootApplication
public class WebfluxApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

}
