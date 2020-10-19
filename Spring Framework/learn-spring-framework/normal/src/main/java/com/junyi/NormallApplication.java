package com.junyi;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @time: 2020/8/17 20:15
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@EnableScheduling
@Configuration
public class NormallApplication {
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
