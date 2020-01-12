package com.junyi.context.hierarchy.demo.up;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class UpAspect {
    @AfterReturning("bean(testBean*)")
    public void printAfter() {
        log.info("After hello()");
    }
}
