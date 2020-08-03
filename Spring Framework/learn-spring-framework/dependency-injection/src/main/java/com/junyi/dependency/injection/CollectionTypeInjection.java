package com.junyi.dependency.injection;

import com.junyi.dependency.domain.Book;
import com.junyi.dependency.holder.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 集合类型
 */
public class CollectionTypeInjection {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:\\META-INF\\collection-type-injection.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        Book book = beanFactory.getBean(Book.class);
        System.out.println(book.toString());
    }
}
