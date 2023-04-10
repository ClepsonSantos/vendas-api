package com.blac.vendasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApiApplication.class, args);
	}

}
