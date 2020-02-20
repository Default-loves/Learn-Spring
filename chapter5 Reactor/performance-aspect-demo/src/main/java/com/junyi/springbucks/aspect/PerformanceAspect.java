package com.junyi.springbucks.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
//只添加Aspect无法将这个类变成一个Bean，需要配合Component
@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime =System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toLongString();
            return pjp.proceed();
        } catch (Throwable throwable) {
            result = "N";
            throw throwable;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}", name, result, endTime-startTime);
        }
    }

    @Pointcut("execution(* com.junyi.springbucks.repository..*(..))")
    private void repositoryOps() {}
}
