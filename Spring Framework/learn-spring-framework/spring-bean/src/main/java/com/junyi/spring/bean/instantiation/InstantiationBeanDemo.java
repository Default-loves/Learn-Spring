package com.junyi.spring.bean.instantiation;

import com.junyi.ioc.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: 实例化 Bean
 */
public class InstantiationBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:\\META-INF\\bean-instantiation-context.xml");
        User user1 = (User) applicationContext.getBean("bean-by-static-method");
        User user2 = (User) applicationContext.getBean("bean-by-instance-method");
        User user3 = (User) applicationContext.getBean("bean-by-factory-bean");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
