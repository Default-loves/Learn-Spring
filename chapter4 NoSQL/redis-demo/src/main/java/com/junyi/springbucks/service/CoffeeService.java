package com.junyi.springbucks.service;

import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class CoffeeService {
    private static final String CACHE = "coffee_cache";
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll ();
    }
    public Optional<Coffee> findOneCoffee(String name) {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            log.info("Coffee in redis: {}", name);
            return Optional.of(hashOperations.get(CACHE, name));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> list = coffeeRepository.findOne(
            Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Found Coffee: {}", list);
        if (list.isPresent()) {
            log.info("Put Coffee to Redis");
            hashOperations.put(CACHE, name, list.get());
            redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);
        }
        return list;


    }
}
