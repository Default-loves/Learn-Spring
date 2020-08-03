package com.junyi.enent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 注册ApplicationListener
 * 1. 通过ConfigurableApplicationContext API进行注册
 * 2. 将ApplicationListener作为Bean注册
 */
public class RegisterApplicationListenerDemo {

    private static class MyApplicationListener implements ApplicationListener {
        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("MyApplicationListener: " + event);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //1. 通过ConfigurableApplicationContext API进行注册
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("ConfigurableApplicationContext: " + event);
            }
        });
        //2. 将ApplicationListener作为Bean注册
        applicationContext.register(MyApplicationListener.class);
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }
}
