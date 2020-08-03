package com.junyi.dependency.injection;

import com.junyi.dependency.holder.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 自动配置 byName、byType
 */
public class SetterAutoWiring {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:\\META-INF\\setter-autowiring-dependency-injection.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder.toString());
    }
}
