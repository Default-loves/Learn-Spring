package com.junyi.ioc.container;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/2 0002
 * Description: ApplicationContext作为IoC容器
 */
public class AnnotationApplicationContextAsIoCContainer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIoCContainer.class);
        // 启动应用上下文
        applicationContext.refresh();

        lookupCollectionByType(applicationContext);

    }
    /*
     * 通过注解@Bean，定义了一个Bean
     */
    @Bean
    private User user() {
        User user = new User();
        user.setId(1l);
        user.setName("wenxing");
        return user;
    }
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("Look up many object by type: " + users);
        }
    }

}
