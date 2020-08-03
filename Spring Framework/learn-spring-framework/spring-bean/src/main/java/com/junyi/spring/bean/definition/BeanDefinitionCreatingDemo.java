package com.junyi.spring.bean.definition;

import com.junyi.ioc.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: BeanDefinition构建示例
 */
public class BeanDefinitionCreatingDemo {
    public static void main(String[] args) {
        //1. 通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 2)
                .addPropertyValue("name", "xiaogou");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 之后还可以修改BeanDefinition，通过setXXX方法
        //beanDefinition.setBeanClassName();
        //beanDefinition.setScope();


        //2. 通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 3)
                .add("name", "xiaohuang");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
