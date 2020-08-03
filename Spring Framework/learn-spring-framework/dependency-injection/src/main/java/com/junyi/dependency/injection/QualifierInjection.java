package com.junyi.dependency.injection;

import com.junyi.dependency.annotation.UserGroup;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: Qualifier注解使用实例
 * 1. 可以限定依赖对象的名称
 * 2. Bean的逻辑分组
 */
public class QualifierInjection{
    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private User superUser; //primary

    @Autowired
    private Collection<User> allUsers;      //user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;    //user3 + user4 + user5 + user6

    @Autowired
    @UserGroup
    private Collection<User> userGroupUsers;    //user5 + user6

    @Bean
    @Qualifier
    private User user3() {
        return createUser(3);
    }

    @Bean
    @Qualifier
    private User user4() {
        return createUser(4);
    }

    @Bean
    @UserGroup
    private User user5() {
        return createUser(5);
    }
    @Bean
    @UserGroup
    private User user6() {
        return createUser(6);
    }

    private User createUser(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierInjection.class);
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();
        QualifierInjection demo = applicationContext.getBean(QualifierInjection.class);

        System.out.println(demo.user);
        System.out.println(demo.superUser);
        System.out.println(demo.allUsers);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.userGroupUsers);

        applicationContext.close();
    }

}
