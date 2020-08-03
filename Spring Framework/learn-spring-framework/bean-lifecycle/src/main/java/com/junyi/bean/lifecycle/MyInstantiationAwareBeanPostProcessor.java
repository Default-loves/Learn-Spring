package com.junyi.bean.lifecycle;

import com.junyi.bean.lifecycle.domain.Book;
import com.junyi.bean.lifecycle.domain.Merchant;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * User: JY
 * Date: 2020/7/9 0009
 * Description: 实例化和初始化阶段的方法回调
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 拦截了“superUser”这个Bean的实例化操作，替换掉了IoC容器产生的实例
        if (ObjectUtils.nullSafeEquals("merchant", beanName) && Merchant.class.equals(beanClass)) {
            Merchant merchant = new Merchant();
            merchant.setId(999l);
            return merchant;
        }
        return null;    //返回null表示，保持Spring Ioc容器的实例化操作
    }
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("merchant", beanName) && Merchant.class.equals(bean.getClass())) {
                Merchant merchant = (Merchant) bean;
                merchant.setId(-1l);
                return false;   //返回false，不使用的IoC属性赋值操作
            }
        return true;
    }
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        if (ObjectUtils.nullSafeEquals("book", beanName) && Book.class.equals(bean.getClass())) {
            final MutablePropertyValues mutablePropertyValues;
            if (pvs instanceof MutablePropertyValues) {
                mutablePropertyValues = (MutablePropertyValues) pvs;
            } else {
                mutablePropertyValues = new MutablePropertyValues();
            }
            // 等价于<property name="id" value="33"/>
            mutablePropertyValues.addPropertyValue("id", "33");
            // 如果bean有“description”属性的配置，那么将原来的配置删除，并且添加新的配置
            if (mutablePropertyValues.contains("description")) {
                mutablePropertyValues.removePropertyValue("description");
                mutablePropertyValues.addPropertyValue("description", "change to V8");
                System.out.println("postProcessProperties(): V" + 8);
            }
            return  mutablePropertyValues;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("book", beanName) && Book.class.equals(bean.getClass())) {
            Book book = (Book) bean;
            book.setDescription("change to V9");
            System.out.println("postProcessBeforeInitialization(): V" + 9);
        }
        return bean;
    }
}
