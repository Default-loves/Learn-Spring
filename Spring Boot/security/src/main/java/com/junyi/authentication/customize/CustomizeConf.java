package com.junyi.authentication.customize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>自定义用户认证方案</p>
 * @time: 2021/3/6 15:33
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Configuration
public class CustomizeConf extends WebSecurityConfigurerAdapter{

    @Autowired
    MyUserDetailsService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(service);
    }
}
