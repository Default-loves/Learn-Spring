package com.junyi.annatation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * User: JY
 * Date: 2020/7/16 0016
 * Description:
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.junyi.annatation.HelloWorldConfiguration"};
    }
}
