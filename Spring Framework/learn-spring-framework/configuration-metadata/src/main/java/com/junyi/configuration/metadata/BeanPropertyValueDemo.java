package com.junyi.configuration.metadata;

import com.junyi.ioc.domain.Book;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * User: JY
 * Date: 2020/7/10 0010
 * Description: Bean属性元信息实例
 * 主要演示：
 * {@link org.springframework.core.AttributeAccessor}
 * {@link BeanMetadataElement}
 */
public class BeanPropertyValueDemo {
    public static void main(String[] args) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Book.class);
        builder.addPropertyValue("description", "BeanPropertyValueDemo");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        //附加属性，不影响Bean populate，initiallize
        beanDefinition.setAttribute("description", "Bean属性元信息");
        //附加属性，设置来源
        beanDefinition.setSource(BeanPropertyValueDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("book", beanDefinition);
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(beanName, "book") && Book.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanPropertyValueDemo.class.equals(bd.getSource())) {
                        String description = (String) bd.getAttribute("description");
                        Book book = (Book) bean;
                        book.setDescription(description);
                    }
                }
                return bean;
            }
        });

        Book book = beanFactory.getBean("book", Book.class);
        //如果没有BeanPostProcessor，那么会输出原本的内容，有了之后会赋值为AttributeAccessor中的值
        System.out.println(book);
    }
}
