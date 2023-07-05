package com.apirest.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebfluxApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

}
