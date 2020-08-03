package com.junyi.spring.bean.definition;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: Spring Bean的注册
 */
@Import(BeanDefinitionRegistryDemo.Config1.class)
public class BeanDefinitionRegistryDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //方式一：XML配置
        //方式二：注解配置元信息
        BeanAnnotaion(applicationContext);
        ComponentAnnotation(applicationContext);
        ImportAnnotation(applicationContext);

        //方式三：Java API配置元信息
//        registerUserBeanDefinition(applicationContext, "jy");
//        registerUserBeanDefinition(applicationContext);

//        applicationContext.refresh();
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.out.println("The type is User in all bean: " +beansOfType);
        applicationContext.close();
    }

    private static void registerUserBeanDefinition(AnnotationConfigApplicationContext applicationContext) {
        registerUserBeanDefinition(applicationContext, null);
    }


    /**
     * Java API方式定义
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", "9").addPropertyValue("name", "java api bean definition");
        if (StringUtils.hasText(beanName)) {
            //命名方式
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            //非命名方式，自动生成名字
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }
    /**
     * //方式一：通过@Bean方式定义
     * @param applicationContext
     */
    public static void BeanAnnotaion(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(Config1.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("user-annotation-bean", User.class);
        System.out.println(user);
    }
    /**
     * //方式二：通过@Component方式定义
     * @param applicationContext
     */
    private static void ComponentAnnotation(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(Config2.class);
        applicationContext.refresh();
        Map<String, Config2> user = applicationContext.getBeansOfType(Config2.class);
        System.out.println(user);
    }

    /**
     * //方式三：通过@Import方式定义
     * @param applicationContext
     */
    private static void ImportAnnotation(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(BeanDefinitionRegistryDemo.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("user-annotation-bean", User.class);
        System.out.println(user);
    }


    public static class Config1 {
        @Bean(name = {"user", "user-annotation-bean"})
        public User user() {
            User user = new User();
            user.setId(3l);
            user.setName("user-annotation-bean");
            return user;
        }
    }
    @Component
    public static class Config2 {

        public User user() {
            User user = new User();
            user.setId(3l);
            user.setName("user-annotation-component");
            return user;
        }
    }
}
