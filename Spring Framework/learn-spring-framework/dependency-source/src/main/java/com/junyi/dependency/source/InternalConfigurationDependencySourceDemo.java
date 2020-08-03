package com.junyi.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * User: JY
 * Date: 2020/7/7 0007
 * Description: 外部化配置作为依赖来源
 * /@Value注解读取.properties文件中的元信息
 */
@Configuration
@PropertySource(value = "META-INF\\default.properties", encoding = "UTF-8")
public class InternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")     //“:”后面的是默认值
    private Long id;

    @Value("${usr.name:default name}")  //这儿会优先读取system properties的一些默认属性，user.name是操作系统的用户名
    private String title;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InternalConfigurationDependencySourceDemo.class);

        applicationContext.refresh();
        InternalConfigurationDependencySourceDemo demo = applicationContext.getBean(InternalConfigurationDependencySourceDemo.class);
        System.out.println(demo.id);
        System.out.println(demo.title);
        System.out.println(demo.resource);

        applicationContext.close();
    }
}
