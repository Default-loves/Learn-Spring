package com.junyi.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/29 0029
 * Description:
 */
public class AnnotationValueDemo {
    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationValueDemo.class);
        applicationContext.refresh();
        AnnotationValueDemo demo = applicationContext.getBean(AnnotationValueDemo.class);
        System.out.println(demo.name);
        applicationContext.close();
    }
}
