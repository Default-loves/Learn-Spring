package com.junyi;

import com.junyi.entity.Water;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @time: 2021/1/16 14:33
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
public class WaterServiceImpl implements WaterService {

        @Autowired
        private WaterMapper waterMapper;

        @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id")
        @Override
        public int update(Integer id, Water water) {
            water.setId(id);
            return 1;
        }

        @CachePut(value = RedisConfig.REDIS_KEY_DATABASE, key = "'water:brand:'+#water.id")
        @Override
        public Water insert(Water water) {
            return water;
        }

        @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'water:brand:'+#id")
        @Override
        public int delete(Integer id) {
            return 1;
        }

        @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id", unless = "#result==null")
        @Override
        public Water getItem(Integer id) {
            return waterMapper.selectByPrimaryKey(id);
        }

    }