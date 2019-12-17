package com.junyi.simple.controller.demo.service;

import com.junyi.simple.controller.demo.model.Coffee;
import com.junyi.simple.controller.demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = "coffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cacheable
    public List<Coffee> findAll() {
        return coffeeRepository.findAll(Sort.by("id"));
    }
    public List<Coffee> findCoffeeByName(List<String> list) {
        return coffeeRepository.findByNameInOrderById(list);
    }
}
