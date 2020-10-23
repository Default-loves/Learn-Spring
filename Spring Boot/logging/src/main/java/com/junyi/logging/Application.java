package com.junyi.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @time: 2020/9/21 11:30
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */

/**
 * Spring Boot工程自带logback和slf4j的依赖
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.debug("apple");
        log.info("apple");
        log.warn("apple");
        log.error("apple");
    }
}
