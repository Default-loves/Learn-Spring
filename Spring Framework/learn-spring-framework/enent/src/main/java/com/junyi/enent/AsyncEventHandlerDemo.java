package com.junyi.enent;

import com.junyi.enent.customized.MySpringEvent;
import com.junyi.enent.customized.MySpringEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 异步对Spring事件广播
 */
public class AsyncEventHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener(new MySpringEventListener());
        applicationContext.refresh();
        ApplicationEventMulticaster applicationEventMulticaster =
                applicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            //设置异步线程池
            ExecutorService executorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-event-thread-pool"));
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            //需要手动在上下文关闭后停止线程池
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!executorService.isShutdown()) {
                        executorService.shutdown();
                    }
                }
            });
        }

        applicationContext.publishEvent(new MySpringEvent("Hi you~"));
        applicationContext.close();
    }
}
