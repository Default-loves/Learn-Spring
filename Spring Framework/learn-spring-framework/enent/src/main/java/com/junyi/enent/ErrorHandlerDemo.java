package com.junyi.enent;

import com.junyi.enent.customized.MySpringEvent;
import com.junyi.enent.customized.MySpringEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ErrorHandler;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: Spring 事件异常处理
 */
public class ErrorHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener(new MySpringEventListener());
        applicationContext.refresh();

        ApplicationEventMulticaster applicationEventMulticaster =
                applicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            simpleApplicationEventMulticaster.setErrorHandler(new ErrorHandler() {
                @Override
                public void handleError(Throwable t) {
                    System.out.println("Spring event error: " + t.getMessage());
                }
            });
        }

        applicationContext.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        });

        applicationContext.publishEvent(new MySpringEvent("Hi you~"));
        applicationContext.close();
    }
}
