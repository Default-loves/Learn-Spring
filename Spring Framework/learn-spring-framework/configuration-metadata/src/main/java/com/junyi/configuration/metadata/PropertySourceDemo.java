package com.junyi.configuration.metadata;

import com.junyi.ioc.domain.Book;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: PropertySource 实例
 * user.name的取值按照my-first-property-source、systemProperties、systemEnvironment、META-INF/default.properties这个顺序取
 */
@PropertySource("classpath:\\META-INF\\default.properties")
public class PropertySourceDemo {
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

        // 在refresh之前对PropertySource进行添加
        Map<String, Object> myData = new HashMap<>();
        myData.put("user.name", "abc");
        org.springframework.core.env.PropertySource<?> myPropertySource =
                new MapPropertySource("my-first-property-source", myData);
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        propertySources.addFirst(myPropertySource);

        applicationContext.register(PropertySourceDemo.class);
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        // Environment中有4个PropertySource：my-first-property-source、systemProperties、systemEnvironment、META-INF/default.properties
        // 这几个PropertySource是有顺序的，前面的优先使用
        System.out.println(propertySources);
        applicationContext.close();
    }
}
