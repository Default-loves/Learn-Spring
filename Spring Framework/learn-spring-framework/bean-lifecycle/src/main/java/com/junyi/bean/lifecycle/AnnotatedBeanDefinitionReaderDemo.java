package com.junyi.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description: AnnotatedBeanDefinitionReader实例
 * 用于注解的BeanDefinition解析
 */
public class AnnotatedBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beforeNumber = beanFactory.getBeanDefinitionCount();
        //注册BeanDefinition，非@Component class
        reader.register(AnnotatedBeanDefinitionReaderDemo.class);
        int afterNumber = beanFactory.getBeanDefinitionCount();

        System.out.println("Load bean definition number: " + (afterNumber - beforeNumber));
        AnnotatedBeanDefinitionReaderDemo demo = beanFactory.getBean("annotatedBeanDefinitionReaderDemo", AnnotatedBeanDefinitionReaderDemo.class);
        System.out.println(demo);
    }
}
