package com.junyi.config;

import com.junyi.LimitRequestInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @time: 2021/1/30 16:50
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    LimitRequestInteceptor limitRequestInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitRequestInteceptor);
    }
}
