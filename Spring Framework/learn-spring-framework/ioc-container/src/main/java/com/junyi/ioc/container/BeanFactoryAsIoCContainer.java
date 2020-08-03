package com.junyi.ioc.container;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/2 0002
 * Description: BeanFactory作为IoC容器
 */
public class BeanFactoryAsIoCContainer {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:\\META-INF\\dependency-lookup-context.xml";
        int beanNumber = reader.loadBeanDefinitions(location);
        System.out.println("The number of bean: " + beanNumber);
        lookupCollectionByType(beanFactory);

    }
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("Look up many object by type: " + users);
        }
    }
}
