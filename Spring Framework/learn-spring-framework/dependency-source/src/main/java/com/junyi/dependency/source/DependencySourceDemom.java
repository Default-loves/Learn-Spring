package com.junyi.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * User: JY
 * Date: 2020/7/7 0007
 * Description: 依赖注入来源实例：ResolvableDependency
 * 在AbstractApplicationContext#prepareBeanFactory中可以发现Spring注册了4个非Spring容器管理对象(ResolvableDependency)，其不能被依赖查找到
 */
public class DependencySourceDemom {
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    private void initByInjection() {
        System.out.println("ApplicationContext == BeanFactory: " + (applicationContext == beanFactory));
        System.out.println("ApplicationContext.getAutowireCapableBeanFactory() == BeanFactory: " + (applicationContext.getAutowireCapableBeanFactory() == beanFactory));
        System.out.println("ApplicationContext == ResourceLoader: " + (applicationContext == resourceLoader));
        System.out.println("ApplicationContext == ApplicationEventPublisher: " + (applicationContext == applicationEventPublisher));
    }
    @PostConstruct
    private void initByLookUp() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }
    private <T> T getBean(Class<T> classType) {
        try {
            beanFactory.getBean(classType);
        } catch (NoSuchBeanDefinitionException exception) {
            System.err.println("In BeanFactory can't find type: " + classType.getName());
        }
        return null;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemom.class);

        applicationContext.refresh();

        applicationContext.close();

    }
}
