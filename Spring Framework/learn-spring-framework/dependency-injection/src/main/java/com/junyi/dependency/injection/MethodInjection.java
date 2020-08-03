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
 * Description: 方法注入
 */
public class MethodInjection {
    private UserHolder userHolder1;
    private UserHolder userHolder2;

    @Autowired
    private void initUserHolder1(UserHolder userHolder) {
        this.userHolder1 = userHolder;
    }
    @Resource
    private void initUserHolder2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MethodInjection.class);
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.register(MethodInjection.class);
        applicationContext.refresh();

        MethodInjection demo = applicationContext.getBean(MethodInjection.class);
        System.out.println(demo.userHolder1.toString());
        System.out.println(demo.userHolder2.toString());
        System.out.println(demo.userHolder1 == demo.userHolder2);
        applicationContext.close();
    }


}
