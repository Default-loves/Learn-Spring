package com.junyi.configuration.metadata.entensiblexml;

import com.junyi.ioc.domain.User;
import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * User: JY
 * Date: 2020/7/11 0011
 * Description: user.xsd文件中User元素解析
 */
public class UserBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        resolvePropertyValue("id", element, builder);
        resolvePropertyValue("name", element, builder);
        resolvePropertyValue("hobby", element, builder);
        resolvePropertyValue("description", element, builder);
    }

    private void resolvePropertyValue(String elementName, Element element, BeanDefinitionBuilder builder) {
        String elementAttribute = element.getAttribute(elementName);
        if (StringUtils.hasText(elementAttribute)) {
            builder.addPropertyValue(elementName, elementAttribute);
        }
    }
}
