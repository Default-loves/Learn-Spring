package com.junyi.enent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: 基于接口的ApplicationListener示例
 * @see ApplicationListener
 */
public class ApplicationListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("Application Event: " + event);
            }
        });
        applicationContext.refresh();
        applicationContext.close();
    }
}
