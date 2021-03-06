package com.junyi.authentication;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 在内存中保存用户信息
 * <p>
 * @time: 2021/3/6 14:37
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class InMemory extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("abc1").password("abc1@123").roles("USER")
                .and()
                .withUser("abc2").password("abc2@123").roles("ADMIN");
    }
}
