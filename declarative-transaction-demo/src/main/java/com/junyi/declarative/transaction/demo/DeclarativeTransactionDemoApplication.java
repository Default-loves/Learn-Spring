package com.junyi.declarative.transaction.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class DeclarativeTransactionDemoApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private FooService fooService;

	public static void main(String[] args) {
		SpringApplication.run(DeclarativeTransactionDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fooService.insertRecord();
		log.info("AAA: {}", jdbcTemplate.queryForObject("select count(*) from FOO where BAR='aaa')", Long.class));
		fooService.insertThenRollback();
		log.info("BBB: {}", jdbcTemplate.queryForObject("select count(*) from FOO where BAR='bbb')", Long.class));
		fooService.invokeInsertThenRollback();
		log.info("BBB: {}", jdbcTemplate.queryForObject("select count(*) from FOO where BAR='bbb')", Long.class));
	}
}
