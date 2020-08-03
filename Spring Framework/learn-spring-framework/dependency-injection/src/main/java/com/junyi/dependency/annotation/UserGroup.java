package com.junyi.dependency.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 用户组
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}
