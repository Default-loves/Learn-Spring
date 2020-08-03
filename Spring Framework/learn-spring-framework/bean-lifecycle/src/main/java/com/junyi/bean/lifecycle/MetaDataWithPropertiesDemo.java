package com.junyi.bean.lifecycle;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description: 通过properties文件配置元信息
 */
public class MetaDataWithPropertiesDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String resourcePath = "META-INF\\default.properties";
        Resource resource = new ClassPathResource(resourcePath);
        //指定字符编码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumber = reader.loadBeanDefinitions(encodedResource);
        System.out.println("Load bean number: " + beanNumber);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
