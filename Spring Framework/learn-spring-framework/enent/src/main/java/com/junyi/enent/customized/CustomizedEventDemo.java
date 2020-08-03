package com.junyi.enent.customized;

import org.springframework.context.support.GenericApplicationContext;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 自定义Spring事件
 * @see MySpringEventListener
 * @see MySpringEvent
 */
public class CustomizedEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener(new MySpringEventListener());
        applicationContext.refresh();
        applicationContext.publishEvent(new MySpringEvent("Hi you~"));
        applicationContext.close();
    }
}
