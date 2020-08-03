package com.junyi.configuration.metadata;

import com.junyi.ioc.domain.Book;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/10 0010
 * Description: 基于Java注解的Spring IoC容器配置元信息实例
 * 演示 @ImportResource、@Import、@PropertySource
 */
@ImportResource("classpath:\\META-INF\\default.xml")
@Import(Book.class)
@PropertySource("classpath:\\META-INF\\default.properties")     //这儿只是演示可以同时配置多个@PropertySource
@PropertySource("classpath:\\META-INF\\default.properties")
public class AnnotationSpringIoCMetadataConfigurationDemo {
    @Bean
    public User propertySourceUser(@Value("${user.id}") Long id, @Value("${user.description}") String description) {
        User user = new User();
        user.setId(id);
        user.setDescription(description);
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationSpringIoCMetadataConfigurationDemo.class);
        applicationContext.refresh();
        Map<String, Book> beans = applicationContext.getBeansOfType(Book.class);
        for (Map.Entry<String, Book> entry: beans.entrySet()) {
            System.out.printf("Key : %s | Value: %s\n", entry.getKey(), entry.getValue());
        }
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
