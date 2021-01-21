package com.junyi;

import org.springframework.context.annotation.Configuration;

/**
 * @time: 2021/1/16 13:42
 * @version: 1.0
 * @author: junyi Xu
 * @description: Redis Sentinal 哨兵
 */
@Configuration
public class SentinalConfig {

    /*
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("my-master")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
    }
     */

}
