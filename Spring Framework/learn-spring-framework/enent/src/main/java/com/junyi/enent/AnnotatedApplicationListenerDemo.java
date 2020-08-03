package com.junyi.enent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 基于注解的ApplicationListener示例
 * \@Order的值越小，优先执行
 */
@EnableAsync
public class AnnotatedApplicationListenerDemo {

    @EventListener
    @Order(3)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        printWithThread("ContextRefreshedEvent: " + event);
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        printWithThread("ContextRefreshedEvent1: " + event);
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        printWithThread("ContextRefreshedEvent(Async): " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        printWithThread("ContextStartedEvent: " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        printWithThread("ContextClosedEvent: " + event);
    }

    private void printWithThread(String msg) {
        System.out.printf("[Thread %s]: %s\n", Thread.currentThread().getName(), msg);
    }
    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-pool"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedApplicationListenerDemo.class);
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }

}
