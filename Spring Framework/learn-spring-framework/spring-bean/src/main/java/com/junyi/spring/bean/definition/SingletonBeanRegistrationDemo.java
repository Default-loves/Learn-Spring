package com.junyi.spring.bean.definition;

import com.junyi.spring.bean.factory.DefaultUserFactory;
import com.junyi.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: 外部单体对象注册成Bean
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //外部的单体对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        //注册外部单体对象
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();

        //依赖查找
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);

        System.out.println(userFactory == userFactoryByLookup);

        applicationContext.close();

    }
}
