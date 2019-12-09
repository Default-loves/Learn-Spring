package com.junyi.programmatic.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/*
编程式事务
 */

@Slf4j
@SpringBootApplication
public class ProgrammaticTransactionApplication implements CommandLineRunner {
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(ProgrammaticTransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Before transaction : {}", getCount());
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				jdbcTemplate.execute("insert into FOO(ID, BAR) values (1, 'abc')");
				log.info("In transaction : {}", getCount());
				transactionStatus.setRollbackOnly();
			}
		});
		log.info("After transaction: {}", getCount());
	}

	private long getCount() {
		return (long) jdbcTemplate.queryForList("select count(*) as cnt from FOO").get(0).get("cnt");
	}
}
