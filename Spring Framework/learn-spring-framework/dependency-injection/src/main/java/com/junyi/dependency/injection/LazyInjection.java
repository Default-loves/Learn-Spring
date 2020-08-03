package com.junyi.dependency.injection;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 延迟依赖注入
 */
public class LazyInjection {
    @Autowired
    private User user;  //实时注入

    @Autowired
    private ObjectProvider<User> objectProvider;    //延迟依赖注入，ObjectProvider

    @Autowired
    private ObjectFactory<User> objectFactory;

    @Autowired
    private ObjectFactory<Set<User>> setObjectFactory;



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyInjection.class);
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();
        LazyInjection demo = applicationContext.getBean(LazyInjection.class);

        System.out.println(demo.user);
        System.out.println(demo.objectProvider.getObject());
        demo.objectProvider.forEach(System.out::println);
        System.out.println(demo.objectFactory.getObject());
        System.out.println(demo.setObjectFactory.getObject());

        applicationContext.close();
    }

}
