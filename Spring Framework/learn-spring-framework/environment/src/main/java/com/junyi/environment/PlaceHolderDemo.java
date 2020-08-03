package com.junyi.environment;

import com.junyi.ioc.domain.User;
import org.omg.CORBA.Environment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * User: JY
 * Date: 2020/7/16 0016
 * Description: 占位符的示例
 * @see PropertySourcesPlaceholderConfigurer
 */
public class PlaceHolderDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:\\META-INF\\user.xml");
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        applicationContext.getEnvironment();
        applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(user);
        applicationContext.close();
    }
}
