package com.junyi;

import com.alibaba.fastjson.JSON;
import com.junyi.annotation.LimitRequest;
import com.junyi.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @time: 2021/1/30 16:31
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class LimitRequestInteceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            //获取方法中的注解,看是否有该注解
            LimitRequest limitRequest = hm.getMethodAnnotation(LimitRequest.class);
            if(limitRequest == null){
                return true;
            }
            int seconds = limitRequest.seconds();
            int maxCount = limitRequest.maxCount();
            boolean login = limitRequest.needLogin();
            String key = request.getRequestURI();
            //如果需要登录
            if(login){
                //获取登录的session进行判断
                //.....
                key+=""+"1";  //这里假设用户是1,项目中是动态获取的userId
            }

            //从redis中获取用户访问的次数
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            String cnt = (String) ops.get(key);

            if(cnt == null){
                //第一次访问
                ops.set(key, "1", seconds, TimeUnit.SECONDS);
            }else if(Integer.parseInt(cnt)  < maxCount){
                //加1
                ops.increment(key);
            }else{
                //超出访问次数
                render(response); //这里的CodeMsg是一个返回参数
                return false;
            }
        }
        return true;

    }
    private void render(HttpServletResponse response)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(Result.error("拒绝访问"));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
