package com.junyi.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @time: 2021/1/26 9:45
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
@Builder
@RedisHash("food")
public class Food {
    @Id
    private Integer id;
    private String name;
    private Integer count;
    private Creator creator;
}
