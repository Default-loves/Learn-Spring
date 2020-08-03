package com.junyi.dependency.holder;

import com.junyi.ioc.domain.User;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 管理User类
 */
public class UserHolder {
    private User user;

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
