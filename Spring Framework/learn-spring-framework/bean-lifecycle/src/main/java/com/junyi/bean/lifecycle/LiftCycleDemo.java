package com.junyi.bean.lifecycle;

import com.junyi.bean.lifecycle.domain.Book;
import com.junyi.bean.lifecycle.domain.Merchant;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * User: JY
 * Date: 2020/7/9 0009
 * Description: Bean生命周期实例
 */
public class LiftCycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //实例化BeanPostProcess
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //销毁BeanPostProcess
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //@Preconstruct、@PreDestroy的支持
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlResourcePath = "classpath:\\META-INF\\default.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        Book book = beanFactory.getBean("book", Book.class);
        Merchant merchant = beanFactory.getBean("merchant", Merchant.class);
        System.out.println(book.toString());
        System.out.println(merchant.toString());

        //手动调用销毁操作(容器内)
        beanFactory.destroyBean("book", book);
        //销毁了并不意味着垃圾回收了
        System.out.println(book.toString());

        //垃圾回收
        Thread.sleep(2000L);
        System.gc();    //会触发对象的finalize()回调方法
        Thread.sleep(2000L);
    }
}
