package com.junyi.context.hierarchy.demo.up;

import com.junyi.context.hierarchy.demo.context.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class UpConfig {
    @Bean
    public TestBean testBeanX() {
        return new TestBean("Up");
    }
    @Bean
    public TestBean testBeanY() {
        return new TestBean("Up");
    }
    @Bean
    public UpAspect upAspect(){
        return new UpAspect();
    }
}
