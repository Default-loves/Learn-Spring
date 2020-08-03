package com.junyi.configuration.metadata.entensiblexml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: user.xsd命名空间处理器
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
