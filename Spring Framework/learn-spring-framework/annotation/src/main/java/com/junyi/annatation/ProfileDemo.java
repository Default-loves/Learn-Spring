package com.junyi.annatation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * User: JY
 * Date: 2020/7/16 0016
 * Description: @Profile 示例
 * @see Profile
 * 一般我们不会在代码中写死Profile配置，而是通过外部化配置文件来配置，通过在外部化配置文件中设置spring.profiles.active=even来配置Profile
 */
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProfileDemo.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //兜底策略
        environment.setDefaultProfiles("odd");
        //活跃的优先考虑
        // 也可以通过 -Dspring.profiles.active=even来执行
//        environment.setActiveProfiles("even");
        applicationContext.refresh();
        Integer number = applicationContext.getBean("number", Integer.class);
        System.out.println(number);
        applicationContext.close();
    }

    @Bean("number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }
    @Bean("number")
    @Profile("even")
    public Integer even() {
        return 4;
    }

}
