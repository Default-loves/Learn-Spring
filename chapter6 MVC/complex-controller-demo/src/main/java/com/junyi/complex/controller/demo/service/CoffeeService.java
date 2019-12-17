package com.junyi.complex.controller.demo.service;

import com.junyi.complex.controller.demo.model.Coffee;
import com.junyi.complex.controller.demo.repository.CoffeeRepository;
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
    public Coffee getCoffee(String name) {
        return coffeeRepository.findByName(name);
    }
    public Coffee getCoffee(Long id) {
        return coffeeRepository.getOne(id);
    }

    public List<Coffee> findCoffeeByName(List<String> list) {
        return coffeeRepository.findByNameInOrderById(list);
    }
}
