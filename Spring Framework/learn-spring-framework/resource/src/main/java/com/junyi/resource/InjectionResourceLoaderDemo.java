package com.junyi.resource;

import com.junyi.resource.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: 依赖注入 ResourceLoader
 * 方法：
 * 1. 注入ApplicationContext作为ResourceLoader；
 * 2. 通过@Autowired将ResourceLoader依赖注入到ResourceLoader对象
 * 3. 实现ResourceLoaderAware回调
 */
public class InjectionResourceLoaderDemo  implements ResourceLoaderAware {

    @Autowired
    private AbstractApplicationContext applicationContext;  //方法1

    @Autowired
    private ResourceLoader resourceLoader;  //方法2

    private ResourceLoader resourceLoaderByAware;   //方法3

    @PostConstruct
    private void init() {
        // 其实三种方法注入的ResourceLoader都是一个对象，本质上都是ApplicationContext的实现类
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
        System.out.println("resourceLoader == resourceLoaderByAware: " + (resourceLoader == resourceLoaderByAware));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceLoaderDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoaderByAware = resourceLoader;
    }
}
