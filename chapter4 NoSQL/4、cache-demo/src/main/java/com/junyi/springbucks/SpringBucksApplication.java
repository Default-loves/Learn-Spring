package com.junyi.springbucks;

import com.junyi.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基于注解来进行缓存
 */

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)	//本质上缓存使用了AOP
public class SpringBucksApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		log.info("All coffee: {}", coffeeService.findAllCoffee().size());
		for (int i = 0; i < 10; i++) {
			log.info("Reading from Cache");
			coffeeService.findAllCoffee();
		}
		log.info("Reloading...");
		coffeeService.reLoad();
		coffeeService.findAllCoffee().forEach(o -> log.info("Coffee: {}", o));
	}



}
