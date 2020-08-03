package com.junyi.annatation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 通过 @Import 引入具体实现，具体实现可以通过以下3种方式实现：
 * - Configuration Class
 * - 基于ImportSelector接口
 * - 基于ImportBeanDefinitionRegistrar接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)
//@Import(HelloWorldImportSelector.class)
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
