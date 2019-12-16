package com.junyi.springbucks.service;

import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

import java.time.Duration;
import java.util.Optional;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
public class CoffeeService {
    private static final String PREFIX = "springbucks-";
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private ReactiveRedisTemplate<String, Coffee> redisTemplate;

    public Flux<Boolean> initCache() {
        return coffeeRepository.findAll()
                .flatMap(c -> redisTemplate.opsForValue()
                .set(PREFIX+c.getName(), c)
                .flatMap(b -> redisTemplate.expire(PREFIX+c.getName(), Duration.ofMinutes(1)))
                .doOnSuccess(r -> log.info("Loading and caching:{}", c)));
    }
    public Mono<Coffee> findOneCoffee(String name) {
        return redisTemplate.opsForValue().get(name)
                .switchIfEmpty(coffeeRepository.findByName(name)
                .doOnSuccess(s -> log.info("Loading from DB:{}",name)));

    }
}
