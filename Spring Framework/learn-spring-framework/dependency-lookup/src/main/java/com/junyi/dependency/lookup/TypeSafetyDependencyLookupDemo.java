package com.junyi.dependency.lookup;

import com.junyi.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/4 0004
 * Description: 安全依赖查找实例
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);
        displayObjectProviderStreamOps(applicationContext);
        displayListableBeanFactoryGetBeasOfType(applicationContext);

        applicationContext.close();
    }
    // 演示ListableBeanFactory#getBeansOfType方法的安全性
    private static void displayListableBeanFactoryGetBeasOfType(AnnotationConfigApplicationContext applicationContext) {
        display("displayListableBeanFactoryGetBeasOfType", () -> applicationContext.getBeansOfType(User.class));
    }

    // 演示ObjectProvider Stream操作的安全性
    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        display("displayObjectProviderStreamOps", () -> objectProvider.forEach(System.out::println));
    }

    // 演示ObjectProvider#getIfAvailable()方法的安全性
    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        display("displayObjectProviderGetIfAvailable", () -> objectProvider.getIfAvailable());
    }

    // 演示ObjectFactory#getObject()方法的安全性
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        display("displayObjectFactoryGetObject", () -> objectFactory.getObject());
    }

    // 演示BeanFactory#getBean()方法的安全性
    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        display("displayBeanFactoryGetBean", () -> applicationContext.getBean(User.class));
    }

    private static void display(String source, Runnable runnable) {
        System.err.println("======================");
        System.err.println("Source from: " + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }
}
