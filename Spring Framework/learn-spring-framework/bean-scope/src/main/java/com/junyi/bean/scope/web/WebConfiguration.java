package com.junyi.bean.scope.web;

import com.junyi.ioc.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User: JY
 * Date: 2020/7/8 0008
 * Description:
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
//    @RequestScope
    @SessionScope
    public User user() {
        User user = new User();
        user.setId(3l);
        user.setName("junyi");
        return user;
    }
}
