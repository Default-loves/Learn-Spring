package com.junyi.more.complex.controller.demo.service;

import com.junyi.more.complex.controller.demo.model.Coffee;
import com.junyi.more.complex.controller.demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
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

    public Coffee addCoffee(String name, Money price) {
        return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

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
