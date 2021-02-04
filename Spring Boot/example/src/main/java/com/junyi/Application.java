package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @time: 2021/1/21 17:00
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
