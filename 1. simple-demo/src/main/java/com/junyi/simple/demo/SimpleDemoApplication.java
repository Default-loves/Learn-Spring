package com.junyi.simple.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SimpleDemoApplication {

	@RequestMapping("/hello")
	String index() {
		return "hello bibi";
	}
	public static void main(String[] args) {
		SpringApplication.run(SimpleDemoApplication.class, args);
	}

}
