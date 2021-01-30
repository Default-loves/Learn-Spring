package com.junyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @time: 2021/1/30 17:07
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
public class RedisService {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, Object> valOpt;

    public Integer get(String key) {
        Object o = valOpt.get(key);
        if (o == null) {
            return null;
        }
        return Integer.parseInt((String) o);
    }

    public void set(String key, String value, int seconds) {
        valOpt.set(key, value, seconds, TimeUnit.SECONDS);

    }

    public void increment(String key) {
        valOpt.increment(key);
    }
}
