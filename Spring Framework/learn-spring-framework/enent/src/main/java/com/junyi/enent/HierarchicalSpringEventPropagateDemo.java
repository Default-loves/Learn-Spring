package com.junyi.enent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 层次性上下文事件传播
 * 当前ApplicationContext的事件会传递到父ApplicationContext，因此事件可能会执行多次
 * 可以对事件源记录来过滤避免多次执行
 */
public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parentContext");
        parentContext.register(MyApplicationEventListen.class);
//        parentContext.register(MyApplicationEventListenExecuteOnce.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("currentContext");
        currentContext.setParent(parentContext);
        currentContext.register(MyApplicationEventListen.class);
//        currentContext.register(MyApplicationEventListenExecuteOnce.class);

        //先启动父，再启动子
        parentContext.refresh();
        currentContext.refresh();

        //先关闭子，再关闭父
        currentContext.close();
        parentContext.close();
    }

    public static class MyApplicationEventListen implements ApplicationListener<ApplicationContextEvent> {

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            System.out.printf("[ %s ]: %s\n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
        }
    }

    public static class MyApplicationEventListenExecuteOnce implements ApplicationListener<ApplicationContextEvent> {
        private static Set<ApplicationContextEvent> set = new HashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (set.add(event)) {
                System.out.printf("[ %s ]: %s\n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }
}
