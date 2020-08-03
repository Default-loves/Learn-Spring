package com.junyi.resource;

import com.junyi.resource.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: 依赖注入 Resource
 */
public class InjectionResourceDemo {

    @Value("classpath:\\META-INF\\default.properties")
    private Resource resourceDefault;

    @Value("classpath*:\\META-INF\\*.properties")
    private Resource[] resourceAll;

    @Value("${user.dir}")
    private String currentProjectRootPath;

    @PostConstruct
    private void init() {
        System.out.println(currentProjectRootPath);
        System.out.println(ResourceUtil.getContext(resourceDefault));
        Stream.of(resourceAll).map(ResourceUtil::getContext).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }
}
