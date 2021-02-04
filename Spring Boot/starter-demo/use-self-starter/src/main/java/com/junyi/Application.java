package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 使用我们自己编写的 starter，my-string-spring-boot-starter
 * @time: 2021/2/3 17:11
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner{

    @Autowired
    MyToolService toolService;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("-------------------------------------");
        log.info("get properties: {}", toolService.getProperties());

        List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        log.info("cal sum: {}", toolService.sum(list));
        log.info("cal gcd: {}", toolService.gcd(16, 12));
        log.info("-------------------------------------");
    }
}
