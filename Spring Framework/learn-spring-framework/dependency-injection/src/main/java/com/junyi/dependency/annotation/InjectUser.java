package com.junyi.dependency.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 * 和MyAutowired注解相比，这儿的注解没有添加Autowired注解
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
