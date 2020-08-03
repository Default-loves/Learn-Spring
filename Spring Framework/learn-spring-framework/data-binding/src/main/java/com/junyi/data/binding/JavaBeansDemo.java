package com.junyi.data.binding;

import com.junyi.ioc.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: JavaBeans实例
 *
 * 基本上是java.beans包下面的东西
 * @see Introspector
 * @see BeanInfo
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
