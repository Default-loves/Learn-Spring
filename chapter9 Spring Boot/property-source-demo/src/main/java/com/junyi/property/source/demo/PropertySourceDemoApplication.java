package com.junyi.property.source.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PropertySourceDemoApplication implements ApplicationRunner {
	@Value("${junyi.name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(PropertySourceDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Read property from another: {}", name);
	}
}
