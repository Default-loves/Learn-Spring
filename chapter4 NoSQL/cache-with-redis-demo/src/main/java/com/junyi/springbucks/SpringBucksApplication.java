package com.junyi.springbucks;

import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.model.CoffeeOrder;
import com.junyi.springbucks.model.OrderState;
import com.junyi.springbucks.repository.CoffeeOrderRepository;
import com.junyi.springbucks.repository.CoffeeRepository;
import com.junyi.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
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
		Thread.sleep(5_000);
		log.info("Reading after refresh");
		coffeeService.reLoad();
		coffeeService.findAllCoffee().forEach(o -> log.info("Coffee: {}", o));
	}



}
