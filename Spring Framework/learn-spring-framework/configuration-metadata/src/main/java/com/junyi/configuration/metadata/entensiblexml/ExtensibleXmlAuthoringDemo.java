package com.junyi.configuration.metadata.entensiblexml;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: Extensible XML authoring 扩展示例
 */
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:\\META-INF\\user-context.xml");

        User user = beanFactory.getBean(User.class);

        System.out.println(user);

    }
}
