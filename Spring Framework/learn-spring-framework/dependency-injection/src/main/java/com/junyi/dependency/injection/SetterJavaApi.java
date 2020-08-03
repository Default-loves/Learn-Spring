package com.junyi.dependency.injection;

import com.junyi.dependency.holder.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: JY
 * Date: 2020/7/6 0006
 * Description: 手动通过 Java API 进行setter依赖注入
 */
public class SetterJavaApi {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:\\META-INF\\dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder.toString());

        applicationContext.close();
    }

    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //Setter注入
        beanDefinitionBuilder.addPropertyReference("user", "superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
