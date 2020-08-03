package com.junyi.spring.bean.definition;

import com.junyi.spring.bean.factory.DefaultUserFactory;
import com.junyi.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: Bean 的初始化
 */
@Configuration
public class BeanInializationAndDestroyDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInializationAndDestroyDemo.class);
        applicationContext.refresh();

        System.out.println("applicationContext refresh finish...");
        System.out.println(applicationContext.getBean(UserFactory.class));
        applicationContext.getBean(UserFactory.class);
        System.out.println("applicationContext is ready to close...");
        applicationContext.close();
        System.out.println("applicationContext is closing...");
    }

    @Bean(initMethod = "initAtBean", destroyMethod = "doDestroy")
    @Lazy(value = false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
