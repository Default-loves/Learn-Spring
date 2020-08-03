package com.junyi.enent.customized;

import org.springframework.context.ApplicationListener;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 自定义事件监听，监听的事件是 MySpringEvent
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {
    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[ %s ]: %s\n", Thread.currentThread().getName(), event.getSource());
    }
}
