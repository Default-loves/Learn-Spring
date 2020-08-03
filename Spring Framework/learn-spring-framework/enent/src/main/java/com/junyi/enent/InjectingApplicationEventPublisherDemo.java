package com.junyi.enent;

import com.junyi.enent.customized.MySpringEvent;
import com.junyi.enent.customized.MySpringEventListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;

import javax.annotation.PostConstruct;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 依赖注入 ApplicationEventPublisher
 * @see ApplicationEventPublisher
 * 方法：
 * 1. @Autowired的方式
 * 2. Aware的方式
 * ApplicationContext是ApplicationEventPublisher的子类
 */
public class InjectingApplicationEventPublisherDemo
        implements ApplicationEventPublisherAware, ApplicationContextAware{
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        //第三、四执行
        applicationContext.publishEvent(new MySpringEvent("Event in: @Autowired ApplicationContext"));
        applicationEventPublisher.publishEvent(new MySpringEvent("Event in: @Autowired ApplicationEventPublisher"));
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectingApplicationEventPublisherDemo.class);
        applicationContext.addApplicationListener(new MySpringEventListener());
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //第一执行
        applicationContext.publishEvent(new MySpringEvent("Event in: ApplicationContextAware"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //第二执行
        applicationEventPublisher.publishEvent(new MySpringEvent("Event in: ApplicationEventPublisherAware"));

    }
}
