package com.junyi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 使用我们自己编写的 starter，my-string-spring-boot-starter
 * @time: 2021/2/3 17:11
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
public class Application implements ApplicationRunner{

    @Autowired
    MyStringService myStringService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] array = myStringService.split(",");
        for (String s : array) {
            System.out.println(s);
        }
    }
}
