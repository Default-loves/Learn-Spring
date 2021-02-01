package com.junyi.config;

import com.junyi.LimitRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 需要注意的是，通过 new 的方式添加，会导致错误，因为通过new的方式创建的对象无法被 BeanFactory 所管理，因此往 limitRequestInterceptor 对象注入的对象会失败
 * @time: 2021/1/30 16:50
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    LimitRequestInterceptor limitRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitRequestInterceptor);
    }
}
