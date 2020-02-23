package com.junyi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用 @Configuration+@Bean 配置Bean
 * @Configuration可理解为用spring的时候xml里面的<beans>标签
 * @Bean可理解为用spring的时候xml里面的<bean>标签
 */
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		Person p = (Person) app.getBean("person");
		System.out.println(p.toString());

		String[] s = app.getBeanNamesForType(Person.class);
		for (String item : s) {
			System.out.println(item);
		}
	}


}
