package com.junyi.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import com.junyi.caffeine.entity.CacheBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @time: 2020/12/21 16:11
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Configuration
public class CacheConfig {

    /**
     * 创建基于Caffeine的Cache Manager
     * 初始化一些key存入
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = Lists.newArrayList();
        List<CacheBean> list = setCacheBean();
        for(CacheBean cacheBean : list){
            caches.add(new CaffeineCache(cacheBean.getKey(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(cacheBean.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(cacheBean.getMaximumSize())
                            .build()));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }


    /**
     * 初始化一些缓存的 key
     * @return
     */
    private List<CacheBean> setCacheBean(){
        List<CacheBean> list = Lists.newArrayList();
        CacheBean userCache = new CacheBean();
        userCache.setKey("userCache");
        userCache.setTtl(60);
        userCache.setMaximumSize(10000);

        CacheBean deptCache = new CacheBean();
        deptCache.setKey("bookCache");
        deptCache.setTtl(60);
        deptCache.setMaximumSize(10000);

        list.add(userCache);
        list.add(deptCache);

        return list;
    }

}
