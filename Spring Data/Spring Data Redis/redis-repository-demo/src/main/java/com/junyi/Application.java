package com.junyi;

import com.junyi.config.MyIndexConfiguration;
import com.junyi.config.MyKeyspaceConfiguration;
import com.junyi.service.FoodExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Redis  Repository Demo
 * 能够快捷得将对象保存到 Redis，保存的时候不设置 id，Redis会自动生成
 * @time: 2021/1/27 15:13
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@SpringBootApplication
@Slf4j
@EnableRedisRepositories(keyspaceConfiguration = MyKeyspaceConfiguration.class, indexConfiguration = MyIndexConfiguration.class)
public class Application  {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    public String get(String key) {
        String value = redis.get(key);
        if (value != null) { //代表缓存值没过期
            return value;
        }
        // 缓存过期，先去获取互斥锁
        //设置3min的超时，防止del操作失败的时候，下次缓存过期一直不能load db
        if (redis.setnx(key_mutex, 1, 3 * 60) == 1) {  //代表设置成功
            value = db.get(key);    // 从数据库获取数据
            redis.set(key, value, expire_secs);
            redis.del(key_mutex);
        } else {  //这个时候代表同时候的其他线程已经loaddb并回设到缓存了，这时候重试获取缓存值即可
            sleep(50);
            get(key);  //重试
        }
    }
}
