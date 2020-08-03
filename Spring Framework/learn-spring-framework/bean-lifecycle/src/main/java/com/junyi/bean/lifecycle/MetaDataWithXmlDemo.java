package com.junyi.bean.lifecycle;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 通过 xml 文件配置Bean元信息
 */
public class MetaDataWithXmlDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:\\META-INF\\default.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        User user = beanFactory.getBean(User.class);
        System.out.println(user.toString());
    }
}
