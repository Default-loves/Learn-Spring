package com.junyi;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @time: 2021/3/6 16:54
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
public class Application implements InitializingBean {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Metrics.addRegistry(new SimpleMeterRegistry());
    }
}
