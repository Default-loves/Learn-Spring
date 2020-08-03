package com.junyi.configuration.metadata;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: 基于@PropertySource 的 YAML 外部化配置示例
 * {@link YamlPropertySourceFactory}
 */
@PropertySource(name = "yaml-property-source",
        value = "classpath:\\META-INF\\user.yaml",
        factory = YamlPropertySourceFactory.class)
public class YamlAnnotatedBasedPropertySourceDemo {

    // user.name为systemProperties中的值
    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.description}") String description) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setDescription(description);
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(YamlAnnotatedBasedPropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
