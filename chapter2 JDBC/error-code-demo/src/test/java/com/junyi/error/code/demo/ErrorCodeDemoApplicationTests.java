package com.junyi.error.code.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorCodeDemoApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test(expected = CustomDuplicatedKeyException.class)
	public void testThrowingCustomException() {
		jdbcTemplate.execute("insert into FOO(ID,BAR) values(1, 'aaa')");
		jdbcTemplate.execute("insert into FOO(ID,BAR) values(1, 'bbb')");
	}
}
