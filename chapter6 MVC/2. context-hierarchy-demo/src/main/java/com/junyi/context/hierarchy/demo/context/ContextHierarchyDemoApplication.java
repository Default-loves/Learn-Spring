package com.junyi.context.hierarchy.demo.context;

import com.junyi.context.hierarchy.demo.up.UpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 层次性的应用上下文AOP实例
 */
@SpringBootApplication
@Slf4j
public class ContextHierarchyDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContextHierarchyDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ApplicationContext upContext = new AnnotationConfigApplicationContext(UpConfig.class);
		ClassPathXmlApplicationContext downContext = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"}, upContext);
		TestBean testBean = upContext.getBean("testBeanX", TestBean.class);
		testBean.hello();
		log.info("----------");

		testBean = downContext.getBean("testBeanY", TestBean.class);
		testBean.hello();

		log.info("----------");

		testBean = downContext.getBean("testBeanX", TestBean.class);
		testBean.hello();
	}
}
