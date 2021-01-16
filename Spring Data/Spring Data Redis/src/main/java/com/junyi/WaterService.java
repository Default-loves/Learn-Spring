package com.junyi;

import com.junyi.entity.Water;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @time: 2021/1/16 14:33
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public interface WaterService {

    int update(Integer id, Water water);

    Water insert(Water water);

    int delete(Integer id);

    Water getItem(Integer id);
}
