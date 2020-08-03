package com.junyi.annatation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/16 0016
 * Description: @EnableXXX 模块实例
 */
@EnableHelloWorld
public class EnableModuleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(EnableModuleDemo.class);
        applicationContext.refresh();
        String helloWorld = applicationContext.getBean("helloWorld", String.class);
        System.out.println(helloWorld);
        applicationContext.close();
    }

}
