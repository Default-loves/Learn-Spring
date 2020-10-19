package com.junyi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @time: 2020/10/19 11:48
 * @version: 1.0
 * @author: junyi Xu
 * @description: use "traceId" to trace the whole method invoke in one request, add "traceId" tag in logback-spring.xml
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private final static String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("add TRACE_ID");
        String traceId = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        ThreadContext.put(TRACE_ID, traceId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadContext. remove(TRACE_ID);
    }
}