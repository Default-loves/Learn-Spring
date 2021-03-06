package com.junyi.authrization;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 授权
 * @time: 2021/3/6 16:17
 * @version: 1.0
 * @author: junyi Xu
 * @description: 授权
 */
@Configuration
public class AuthrizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 访问 /orders 下的资源，需要完成认证后才能访问
        http.authorizeRequests()
                .antMatchers("/orders/**")
                .authenticated()
                ;

        // 使用 SpELl 表达式来设置规则
        http.authorizeRequests()
                .antMatchers("/orders")
                .access("hasRole('ROLE_USER')");

        // 配置所有的请求都需要进行认证
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        // 配置所有的请求都需要认证, 而且请求 customers下的资源还另外需要 admin 的角色
        http.authorizeRequests()
                .antMatchers("/customers/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        // 配置所有的请求都需要认证, 而且删除 customers下的资源还另外需要 admin 的角色
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/customers/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
