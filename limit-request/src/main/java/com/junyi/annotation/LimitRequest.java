package com.junyi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @time: 2021/1/30 16:25
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LimitRequest {
    int seconds();
    int maxCount();
    boolean needLogin() default true;
}
