package com.junyi.ioc.dependency.lookup;

import com.junyi.ioc.annotation.Super;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/1 0001
 * Description:
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置XML配置文件
        // 启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\dependency-lookup-context.xml");

        lookupRealTime(beanFactory);
//        lookupLazy(beanFactory);
//        lookupByType(beanFactory);
//        lookupCollectionByType(beanFactory);
//        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("Look up by annotation: " + users);
        }
    }

    //通过类型查找多个对象
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("Look up many object by type: " + users);
        }
    }

    //通过类型查找单一对象
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("look up by type in real time: " + user);
    }

    //延迟查找
    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("Look up Lazy: " + user);
    }

    //实时查找
    private static void lookupRealTime(BeanFactory beanFactory) {
        Object user = beanFactory.getBean("user");
        System.out.println("Look up in real time: " + user);
    }
}
