package com.junyi.spring.bean.definition;

import com.junyi.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: 垃圾回收
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInializationAndDestroyDemo.class);
        applicationContext.refresh();
        applicationContext.close();
        Thread.sleep(2000L);
        System.gc();    //会触发对象的finalize()回调方法
        Thread.sleep(2000L);
    }
}
