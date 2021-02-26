package com.junyi;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 此时 Spring 容器还没有初始化
 * @time: 2021/2/21 11:26
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class Ini1 implements ApplicationContextInitializer, BeanDefinitionRegistryPostProcessor {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("【ApplicationContextInitializer】");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("【BeanDefinitionRegistryPostProcessor】-【postProcessBeanDefinitionRegistry】");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("【BeanDefinitionRegistryPostProcessor】-【postProcessBeanFactory】");
    }
}
