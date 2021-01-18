package com.junyi;

import com.junyi.entity.Water;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.awt.print.Book;
import java.awt.print.Paper;
import java.util.concurrent.TimeUnit;

/**
 * @time: 2021/1/16 11:49
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
@EnableCaching
@Slf4j
public class Application implements CommandLineRunner {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Autowired
    WaterService waterService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {

        log.info("just test");
        Water water = Water.builder()
                .id(1)
                .desc("abc")
                .build();
//        waterService.insert(water);
//
//        Thread.sleep(10_000);
//        log.info("start to delete");
//        waterService.delete(1);
        redisTemplate.opsForValue().set("water:brand:1", water);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                log.info(connection.dbSize() + "");
                ((RedisConnection) connection).set("")
            }
        })

    }
}
