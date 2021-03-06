package com.junyi.simplejdbcdemo;

import com.junyi.simplejdbcdemo.dao.BatchFooDao;
import com.junyi.simplejdbcdemo.dao.FooDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

/**
 * 简单使用JDBCTemplate来查找数据
 */
@SpringBootApplication
public class SimpleJdbcDemoApplication implements CommandLineRunner {
	@Autowired
	private FooDao fooDao;
	@Autowired
	private BatchFooDao batchFooDao;

	@Autowired
	@Bean
	public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("FOO").usingGeneratedKeyColumns("ID");
	}
	@Bean
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fooDao.insert();
		batchFooDao.insert();
		fooDao.listData();
	}
}
