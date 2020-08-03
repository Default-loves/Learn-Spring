package com.junyi.enent;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: ApplicationEventPublisher 事件发布器，通过Aware回调获取ApplicationEventPublisher
 * @see ApplicationEventPublisher
 */
public class ApplicationEventPublisherDemo implements ApplicationEventPublisherAware {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationEventPublisherDemo.class);

        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("Application Event: " + event.toString());
            }
        });
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hi you~") {});
        applicationEventPublisher.publishEvent("Hi you!");  //出不来
    }
}
