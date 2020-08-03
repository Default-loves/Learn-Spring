package com.junyi.bean.scope;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description: Spring Bean作用域singleton、prototype实例
 *
 * 结论一：
 * Singleton Bean 无论依赖注入或者依赖查找，都是同一个Bean
 * Prototype Bean 无论依赖注入或者依赖查找，都会创建一个新的Bean
 *
 * 结论二：
 * 如果依赖注入集合类型的对象，那么Singleton和 Property各有一个
 * 且prototype bean和其他的prototype bean不一样
 *
 * 结论三：
 * Singleton Bean 和 Prototype Bean均会执行初始化方法回调
 * 但是Prototype Bean 不会执行销毁方法回调，Singleton Bean会执行
 * 为了能够销毁Prototype Bean，一种方法是BeanPostProcess，一种方法是实现依赖于 prototype bean 的 bean 的销毁方法 DisposableBean#destroy()
 * BeanPostProcess的方法执行完后还会使用到Bean，所以在方法里面销毁一些数据其实是不合理的，所以建议使用后者方法
 */
public class BeanScopeDemo implements DisposableBean {
    @Bean
    private static User singletonUser() {  //默认是Singleton
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private static User prototypeUser() {
        return createUser();
    }

    @Autowired
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        // 销毁Prototype Bean方法一
//        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
//                @Override
//                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                    System.out.printf("%s Bean name [%s] invoke after init\n", bean.getClass().getName(), beanName);
//                    return bean;
//                }
//            });
//        });

        applicationContext.refresh();

        scopeDependencyLookup(applicationContext);
        scopeDependencyInjection(applicationContext);

        applicationContext.close();

    }

    private static void scopeDependencyInjection(AnnotationConfigApplicationContext applicationContext) {
        // singletonUser 每次都是一样的
        // prototypeUser 每次都是不一样的
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("Injection, singleton: " +demo.singletonUser);
        System.out.println("Injection, singleton: " +demo.singletonUser1);
        System.out.println("Injection, prototype: " + demo.prototypeUser);
        System.out.println("Injection, prototype: " + demo.prototypeUser1);
        // 如果依赖注入集合类型的对象，那么Singleton和Propotype各有一个
        // 且prototype bean和其他的prototype bean不一样
        System.out.println("Injection, Collection Map: " + demo.users);
    }

    private static void scopeDependencyLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            // singletonUser 每次都是一样的
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println(singletonUser);
            // prototypeUser 每次都是不一样的
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println(prototypeUser);
        }
    }

    // 销毁Prototype Bean方法二，推荐使用
    public void destroy() throws Exception {
        System.out.println("Bean BeanScopeDemo is ready to destroy...");
        this.prototypeUser.doDestroy();
        this.prototypeUser1.doDestroy();
        for(Map.Entry<String, User> entry: this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.doDestroy();
            }
        }
        System.out.println("Bean BeanScopeDemo destroy finish...");

    }
}
