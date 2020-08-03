package com.junyi.configuration.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: 基于XML资源的 YAML 外部化配置示例
 * {@link YamlMapFactoryBean}
 */
public class YamlXmlBasedPropertySourceDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:\\META-INF\\user-context.xml");
        Map yamlMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(yamlMap);
    }
}
