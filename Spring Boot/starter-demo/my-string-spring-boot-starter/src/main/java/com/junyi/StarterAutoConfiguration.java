package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 原理:
 * 1.在应用程序启动过程中，Spring Boot使用SpringFactoriesLoader类加载器查找org.springframework.boot.autoconfigure.EnableAutoConfiguration关键字对应的Java配置文件。Spring Boot会遍历在各个jar包中META-INF目录下的spring.factories文件，构建成一个配置文件链表。
 * 2.根据spring.factories配置加载AutoConfigure类
 * 3.根据 @Conditional注解的条件，进行自动配置并将Bean注入Spring Context中。
 */

/**
 * 配置我们自己的 starter
 * @time: 2021/2/3 16:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Slf4j
@Configuration
@ConditionalOnClass(MyToolService.class)
@EnableConfigurationProperties(MyConfigurationProperties.class)
public class StarterAutoConfiguration {

    @Autowired
    MyConfigurationProperties properties;


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "com.junyi", value = "enable", havingValue = "true")
    MyToolService myToolService() {
        return new MyToolService(properties);
    }
}
