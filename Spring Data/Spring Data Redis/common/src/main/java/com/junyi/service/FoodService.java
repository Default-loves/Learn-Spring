package com.junyi.service;

import com.alibaba.fastjson.JSON;
import com.junyi.entity.Food;
import com.junyi.mapper.FoodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @time: 2021/1/26 9:46
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class FoodService {

    @Autowired
    FoodMapper foodMapper;

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, Object> listOps;

    public Food getOne(Integer id) {
        return foodMapper.getById(id);
    }

    public List<Food> listAll() {
        return foodMapper.lists();
    }

    @Transactional
    public void test(Food food) {
        listOps.leftPush(String.valueOf(food.getId()), JSON.toJSONString(food));
    }

    /**
     * 可以使用这种方法来直接与 Redis 交互
     */
    public void useCallback() {
        stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long size = connection.dbSize();
                // Can cast to StringRedisConnection if using a StringRedisTemplate
                ((StringRedisConnection)connection).set("key", "value");
                return "OK";
            }
        });
    }
}
