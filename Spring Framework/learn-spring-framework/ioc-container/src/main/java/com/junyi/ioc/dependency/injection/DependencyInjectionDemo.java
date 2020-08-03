package com.junyi.ioc.dependency.injection;

import com.junyi.ioc.domain.User;
import com.junyi.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * User: JY
 * Date: 2020/7/1 0001
 * Description: 依赖注入的示例
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置XML配置文件
        // 启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\dependency-injection-context.xml");
        //自定义的Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");

        System.out.println(userRepository.getUsers());
        //userRepository对象里面我们注入了一个BeanFactory，和这儿的BeanFactory又不相等，所以依赖的来源是哪儿？
        //ApplicationContext的抽象类AbstractApplicationContext是组合了BeanFactory的实现DefaultListableBeanFactory，而不是去抽象或者继承BeanFactory
        //ApplicationContext#getBean方法其实是调用了getBeanFactory()方法，调用了BeanFactory的API
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        //BeanFactory不是一个普通的Bean，而是一个容器内建依赖
        //同时，依赖查找和依赖注入的来源是不一样的
        System.out.println(userRepository.getBeanFactory());    //依赖注入
//        System.out.println(beanFactory.getBean(BeanFactory.class));     //依赖查找，会报错

        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
        System.out.println(userObjectFactory.getObject());

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == beanFactory);   // true，说明ApplicationContext是BeanFactory

        //容器内建Bean对象
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
}
