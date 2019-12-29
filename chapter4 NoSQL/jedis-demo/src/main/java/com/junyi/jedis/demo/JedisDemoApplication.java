package com.junyi.jedis.demo;

import com.junyi.jedis.demo.repository.CoffeeRepository;
import com.junyi.jedis.demo.model.Coffee;
import com.junyi.jedis.demo.model.CoffeeOrder;
import com.junyi.jedis.demo.model.OrderState;
import com.junyi.jedis.demo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement
public class JedisDemoApplication implements ApplicationRunner {

	@Autowired
	private CoffeeRepository coffeeRepository;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private JedisPoolConfig jedisPoolConfig;

	public static void main(String[] args) {
		SpringApplication.run(JedisDemoApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("redis")
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}
	@Bean(destroyMethod = "close")
	public JedisPool jedisPool(@Value("${redis.host}") String host) {
		return new JedisPool(jedisPoolConfig(), host);
	}
	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		log.info(jedisPoolConfig.toString());

		try (Jedis jedis = jedisPool.getResource()) {
			coffeeRepository.findAll().forEach(c -> {
				jedis.hset("springbucks-menu",
						c.getName(), Long.toString(c.getPrice().getAmountMinorLong()));
			});
			Map<String, String> map = jedis.hgetAll("springbucks-menu");
			log.info("Menu:{}", map);
			String price = jedis.hget("springbucks-menu", "mocha");
			log.info("Mocha:{}", Money.ofMajor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
		}
	}

}
