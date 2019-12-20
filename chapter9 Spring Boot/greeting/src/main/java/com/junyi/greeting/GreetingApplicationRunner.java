package com.junyi.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;

@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {
	public GreetingApplicationRunner() {
		log.info("Init Greeting");
	}

	public void run(ApplicationArguments args) throws Exception {
		log.info("hello gay, I'm in GreetingApplicationRunner");
	}
}
