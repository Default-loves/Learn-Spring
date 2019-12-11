package com.junyi.mybatis.demo;

import com.github.pagehelper.PageInfo;
import com.junyi.mybatis.demo.mapper.CoffeeMapper;
import com.junyi.mybatis.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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
		coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3))
				.forEach(o -> log.info("Page 1 coffee: {}", o));

		log.info("-----------");
		coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3))
				.forEach(o -> log.info("Page 2 coffee: {}", o));


		log.info("-----------");
		coffeeMapper.findAllWithRowBounds(new RowBounds(1, 0))
				.forEach(o -> log.info("Page 1 coffee: {}", o));
		log.info("-----------");
		coffeeMapper.findAllWithParam(1, 3)
				.forEach(o -> log.info("Page 1 coffee: {}", o));

		log.info("-----------");
		List<Coffee> lst = coffeeMapper.findAllWithParam(2,3);
		PageInfo pageInfo = new PageInfo(lst);
		log.info("Page info: {}", pageInfo);
	}
}
