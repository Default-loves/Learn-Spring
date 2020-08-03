package com.junyi.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/4 0004
 * Description: 层次性依赖查找实例
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("Current beanFactory's parent is: " + beanFactory.getParentBeanFactory());
        HierarchicalBeanFactory beanFactoryParent = createBeanFactory();
        beanFactory.setParentBeanFactory(beanFactoryParent);
        System.out.println("Current beanFactory's parent is: " + beanFactory.getParentBeanFactory());

        displayContainLocalBean(beanFactory, "user");
        displayContainLocalBean(beanFactoryParent, "user");

        displayContainBean(beanFactory, "user");
        displayContainBean(beanFactoryParent, "user");

        applicationContext.refresh();
        applicationContext.close();
    }

    private static boolean containBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory cast = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containBean(cast, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }
    private static void displayContainBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("Current beanFactory contain bean [%s] : %s\n", beanName, containBean(beanFactory, beanName));
    }
    private static void displayContainLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("Current beanFactory contain local [%s] : %s\n", beanName, beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
