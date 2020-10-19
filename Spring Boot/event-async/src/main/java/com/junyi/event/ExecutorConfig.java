package com.junyi.event;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @time: 2020/9/23 16:10
 * @version: 1.0
 * @author: junyi Xu
 * @description: Custom Executor Pool
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(3);
        // 最大线程数
        taskExecutor.setMaxPoolSize(10);
        // 队列大小
        taskExecutor.setQueueCapacity(15);
        // 线程名的前缀
        taskExecutor.setThreadNamePrefix("async-jy-");
        taskExecutor.initialize();
        return taskExecutor;

    }

    // 捕捉IllegalArgumentException异常
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }

    private class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.info("TASK Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : objects) {
                log.info("Parameter value - " + param);
            }
        }
    }
}
