package com.junyi.bean.lifecycle;

import com.junyi.bean.lifecycle.domain.Book;
import com.junyi.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * User: JY
 * Date: 2020/7/9 0009
 * Description: 销毁前阶段
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        Book book = (Book) bean;
        book.setDescription("change to 10");
        System.out.println("postProcessBeforeDestruction(): " + 10);
    }
}
