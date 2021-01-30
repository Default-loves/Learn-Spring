package com.junyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 限制客户端的请求，主要是用于防刷，防刷的特征是在短时间内大量的请求请求数据，我们可以在Redis中记录访问次数，当超过阈值的时候拒绝访问
 * @time: 2021/1/30 16:20
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
