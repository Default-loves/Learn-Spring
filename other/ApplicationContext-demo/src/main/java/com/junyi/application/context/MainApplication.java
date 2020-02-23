package com.junyi.application.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * ApplicationContext接口的实现类使用
 */
@SpringBootApplication
@Slf4j
public class MainApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		ApplicationContext context = new FileSystemXmlApplicationContext("F:\\abc\\empty-demo\\src\\main\\resources\\beans.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		User user = (User) context.getBean("user");
		log.info(user.toString());

	}
}
