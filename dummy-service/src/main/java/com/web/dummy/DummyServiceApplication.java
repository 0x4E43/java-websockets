package com.web.dummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DummyServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(DummyServiceApplication.class, args);
	}

	@GetMapping
	public String test(){
		return "This end point works";
	}



}
