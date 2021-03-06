package com.junyi.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/** 在数据库中保存用户信息
 * <p>
 * @time: 2021/3/6 14:37
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class InDataSource extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from Users " + "where username=?")
                .authoritiesByUsernameQuery("select username, authority from UserAuthorities " + "where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
