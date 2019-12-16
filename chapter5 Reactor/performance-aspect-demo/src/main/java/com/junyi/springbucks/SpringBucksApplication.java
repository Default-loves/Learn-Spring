package com.junyi.springbucks;

import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.model.CoffeeOrder;
import com.junyi.springbucks.model.OrderState;
import com.junyi.springbucks.repository.CoffeeOrderRepository;
import com.junyi.springbucks.repository.CoffeeRepository;
import com.junyi.springbucks.service.CoffeeOrderService;
import com.junyi.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 使用AOP打印sql的语句运行情况
 */

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringBucksApplication implements ApplicationRunner {

	@Autowired
	private CoffeeRepository coffeeRepository;
	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private CoffeeOrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Find all:{}", coffeeRepository.findAll());
		Optional<Coffee> coffee = coffeeService.findOneCoffee("mocha");
		if (coffee.isPresent()) {
			CoffeeOrder order = orderService.createOrder("Junyi", coffee.get());
			log.info("Update state INIT to PAID", orderService.updateState(order, OrderState.PAID));
			log.info("Update state PAID to INIT", orderService.updateState(order, OrderState.INIT));
		}
	}



}
