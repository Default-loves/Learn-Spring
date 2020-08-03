package com.junyi.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 接口回调的依赖注入实例
 * {@link org.springframework.beans.factory.Aware}
 */
public class AwareInterfaceInjection implements BeanFactoryAware, ApplicationContextAware {
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareInterfaceInjection.class);

        context.refresh();
        AwareInterfaceInjection demo = context.getBean(AwareInterfaceInjection.class);
        System.out.println(demo.beanFactory == context.getBeanFactory());
        System.out.println(demo.applicationContext == context);

        context.close();
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
