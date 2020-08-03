package com.junyi.spring.bean.factory;

import com.junyi.ioc.domain.User;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description: User类的工厂
 */
public interface UserFactory {

    default User createUserDefault() {
        return User.createUser();
    }
}
