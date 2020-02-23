package com.junyi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanPostProcessor接口的使用
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
