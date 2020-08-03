package com.junyi.spring.bean.instantiation;
import com.junyi.spring.bean.factory.DefaultUserFactory;
import com.junyi.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;
/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: 特殊方式实例化 Bean
 * 一种是 ServiceLoaderFactoryBean，一种是AutowiredCapableBeanFactory
 */
public class SpecialInstantiationBeanDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:\\META-INF\\special-bean-instantiation-context.xml");
        //调用ServiceLoaderFactoryBean获取ServiceLoader
        ServiceLoader<UserFactory> userFactoryServiceLoader = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        display(userFactoryServiceLoader);

        //直接调用ServiceLoader
        serviceLoaderDemo();

        //----------
        //通过AutowireCapableBeanFactory实例化Bean
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUserDefault());

    }
    public static void serviceLoaderDemo() {
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        display(load);
    }
    private static void display(ServiceLoader loader) {
        Iterator<UserFactory> iterator = loader.iterator();
        while (iterator.hasNext()) {
            UserFactory next = iterator.next();
            System.out.println(next.createUserDefault());
        }
    }
}
