package com.junyi.dependency.injection;

import com.junyi.dependency.holder.UserHolder;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 手动通过 注解 进行setter依赖注入
 */
public class SetterAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.register(SetterAnnotation.class);
        applicationContext.refresh();
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder.toString());
        applicationContext.close();
    }

    @Bean
    private UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
