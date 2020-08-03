package com.junyi.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.Destroyable;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: User类工厂的默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory is inializing...");
    }

    public void initAtBean() {
        System.out.println("@Bean : UserFactory is inializing...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet : UserFactory is inializing...");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : UserFactory is destroying...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() : UserFactory is destroying...");
    }

    public void doDestroy() {
        System.out.println("customize destroy method : UserFactory is destroying...");
    }

    public void finalize() throws Throwable {
        System.out.println("Method finalize() is running, object is garbage-collecting...");
    }
}
