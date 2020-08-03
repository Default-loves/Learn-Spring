package com.junyi.dependency.injection;

import com.junyi.dependency.holder.UserHolder;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 字段注入
 * Autowired注解会忽略掉静态字段
 */
public class FieldInjection {
    @Autowired
    private UserHolder userHolder1;
    @Resource
    private UserHolder userHolder2;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(FieldInjection.class);
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.register(FieldInjection.class);
        applicationContext.refresh();

        FieldInjection demo = applicationContext.getBean(FieldInjection.class);
        System.out.println(demo.userHolder1.toString());
        System.out.println(demo.userHolder2.toString());
        System.out.println(demo.userHolder1 == demo.userHolder2);
        applicationContext.close();
    }

    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
