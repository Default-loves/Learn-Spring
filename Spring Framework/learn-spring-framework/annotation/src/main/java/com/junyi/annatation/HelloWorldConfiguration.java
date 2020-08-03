package com.junyi.annatation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: JY
 * Date: 2020/7/16 0016
 * Description:
 */
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld() {
        return "Hi, the world~";
    }

}
