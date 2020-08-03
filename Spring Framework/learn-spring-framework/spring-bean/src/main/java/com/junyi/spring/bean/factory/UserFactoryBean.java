package com.junyi.spring.bean.factory;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: User 的 FactoryBean实现
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
