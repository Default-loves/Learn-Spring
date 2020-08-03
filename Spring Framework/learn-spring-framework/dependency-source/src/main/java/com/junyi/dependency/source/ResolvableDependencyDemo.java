package com.junyi.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * User: JY
 * Date: 2020/7/7 0007
 * Description: ResolvableDependency 依赖注入来源实例
 * 添加自定义 ResolvableDependency
 */
public class ResolvableDependencyDemo {

    @Autowired
    private String value;

    @PostConstruct
    private void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencyDemo.class);


        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "junyi");
        });
        //启动应用上下文，当中会执行BeanFactoryPostProcessor，然后才初始化Bean
        applicationContext.refresh();

        applicationContext.close();
    }
}
