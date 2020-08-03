package com.junyi.dependency.injection;

import com.junyi.dependency.annotation.InjectUser;
import com.junyi.dependency.annotation.MyAutowired;
import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Optional;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 自定义依赖注入注解
 */
public class CustomizeInjection {
    @Autowired
    private User user;  //实时注入

    @MyAutowired
    private Optional<User> myAutowiredUser;

    @InjectUser
    private User injectUser;

    /**
     * 方式一：将依赖注入注解重新添加
     * 注意：这儿需要加上static，如果不是static，那么beanPostProcessor会等待CustomizeInjection这个Bean初始化后才能够存在
     * 而如果加上了static，那么beanPostProcessor就脱离了CustomizeInjection的束缚，能够提前进行初始化
     * @return
     */
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 这儿添加的依赖注入注解@Autowired、@InjectUser能够生效
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, InjectUser.class));
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return autowiredAnnotationBeanPostProcessor;
//    }

    /**
     * 方式二：只添加我们自定义的依赖注入注解，这儿需要设置为static，而且指定@Order
     * 这样的话，就会有两个AutowiredAnnotationBeanPostProcessor，一个是默认的，一个是这儿定义的一个
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor =
                new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomizeInjection.class);
        //在注解的场景下，也可以使用xml读取Bean信息，这儿配置了User Bean的信息
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);
        applicationContext.refresh();
        CustomizeInjection demo = applicationContext.getBean(CustomizeInjection.class);

        System.out.println(demo.user);
        System.out.println(demo.myAutowiredUser);
        System.out.println(demo.injectUser);

        applicationContext.close();
    }

}
