package com.junyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @time: 2021/3/12 15:07
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
@Slf4j
public class StartBean implements InitializingBean, ApplicationRunner, CommandLineRunner, ApplicationListener<ContextRefreshedEvent>, SmartLifecycle {


    static {
        log.info("1. static context");
    }

    @PostConstruct
    public void init() {
        log.info("2. @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("3. InitializingBean afterPropertiesSet()");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("7. ApplicationRunner run()");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("8. CommandLineRunner run()");
    }

    @Override
    public void start() {
        log.info("5. SmartLifecycle start()");
    }

    @Override
    public void stop() {
        log.info("SmartLifecycle stop()");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("6. ApplicationListener<ContextRefreshedEvent> onApplicationEvent()");
    }

    /** 这种方式会执行多次(测试是6次)，而其他都是只执行1次， */
    @EventListener
    public void eventListener(ApplicationEvent ContextRefreshedEvent) {
        log.info("4. @EventListener");
    }
}
