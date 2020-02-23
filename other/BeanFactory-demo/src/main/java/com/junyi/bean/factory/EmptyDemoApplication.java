package com.junyi.bean.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

/**
 * XmlBeanFactory类的使用，从xml文件中获取Bean信息
 */
@Slf4j
@SpringBootApplication
public class EmptyDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmptyDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
		User user = (User) beanFactory.getBean("user");
		log.info(user.toString());
	}
}
