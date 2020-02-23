package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过名字自动装配(byName)
 * 一个类中的属性，尝试从配置文件中寻找id为名字和属性名字相同的bean进行装配
 *
 * 通过类型自动装配(byType)
 */
@SpringBootApplication
@Slf4j
public class MainApplication implements ApplicationRunner {
	@Bean
	public Job job1() {
		return new Job("Musicing");
	}
	@Bean
	public Job job2() {
		return new Job("Writing");
	}
	@Autowired
	private User user;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(user.toString());

	}
}
