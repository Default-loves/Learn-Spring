package com.junyi.spring.bean.definition;

import com.junyi.ioc.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: Bean别名示例
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:\\META-INF\\bean-definition-context.xml");
        User user1 = applicationContext.getBean("user", User.class);
        User user2 = applicationContext.getBean("xujunyi-user", User.class);
        System.out.println("The alias user equal user: " + (user1 == user2));

    }
}
