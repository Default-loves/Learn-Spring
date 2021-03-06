package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2021/1/21 17:13
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
//@RefreshScope
@Slf4j
public class Controller {


    @Value("${food.name: default}")
    private String content;

    @Autowired
    MyConfigurationProperties properties;

    @GetMapping("/hello")
    public String hello() {
        return content;
    }

    @GetMapping("/test")
    public void test() {
        log.info("{}, {}", properties.getId(), properties.getName());
    }
}
