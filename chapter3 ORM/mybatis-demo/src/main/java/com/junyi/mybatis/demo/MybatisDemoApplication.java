package com.junyi.mybatis.demo;

import com.junyi.mybatis.demo.mapper.CoffeeMapper;
import com.junyi.mybatis.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MyBatis 使用
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.junyi.mybatis.demo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {
	@Autowired
	private CoffeeMapper coffeeMapper;


	public static void main(String[] args) {
		SpringApplication.run(MybatisDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Coffee c = Coffee.builder().name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
		int i = coffeeMapper.save(c);
		log.info("Save {} coffee: {}", i, c);

		c = Coffee.builder().name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
		i = coffeeMapper.save(c);
		log.info("Save {} coffee: {}", i, c);

		c = coffeeMapper.findById(c.getId());
		log.info("Find by id: {}", c);
	}
}
