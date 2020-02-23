package com.junyi.component.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用xml配置Bean
 */
@SpringBootApplication
public class AbcDemoApplication {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		Person p = (Person) app.getBean("pe");
		System.out.println(p.toString());
	}


}
