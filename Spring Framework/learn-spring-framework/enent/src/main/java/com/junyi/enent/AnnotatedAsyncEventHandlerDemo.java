package com.junyi.enent;

import com.junyi.enent.customized.MySpringEvent;
import com.junyi.enent.customized.MySpringEventListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 使用注解，异步对Spring事件广播
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedAsyncEventHandlerDemo.class);
        applicationContext.refresh();

        applicationContext.publishEvent(new MySpringEvent("Hi you~"));
        applicationContext.close();
    }

    @Async
    @EventListener
    public void onEventHandler(MySpringEvent event) {
        System.out.printf("[ %s ]: %s", Thread.currentThread().getName(), event.getSource());
    }
    @Bean
    public Executor taskExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-event-thread-async-pool"));
        return executorService;
    }
}
