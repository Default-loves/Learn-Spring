package com.junyi;

import com.alibaba.fastjson.JSON;
import com.junyi.annotation.LimitRequest;
import com.junyi.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

/**
 * @time: 2021/1/30 16:31
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
@Slf4j
public class LimitRequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;

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
            String key = generateKey(request);

            //如果需要登录
            if(login){
                //获取登录的session进行判断
                //.....
                key+=""+"1";  //这里假设用户是1,项目中是动态获取的userId
            }

            //从redis中获取用户访问的次数
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String cnt = ops.get(key);

            if(cnt == null){
                //第一次访问
                ops.set(key, "1", seconds, TimeUnit.SECONDS);
            }else if(Integer.parseInt(cnt)  < maxCount){
                //加1
                ops.increment(key, 1L);
            }else{
                //超出访问次数
                render(response);
                return false;
            }
        }
        return true;

    }

    /** 创建 Key，由请求的 ip 和 uri 组成，作为Redis 的Key */
    private String generateKey(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String key = ip + ":" + uri;
        return jdkMD5(key);
    }


    /** 使用 MD5 算法获得摘要 */
    private static String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            log.error("",e);
        }
        return res;
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
