package com.junyi.service;

import com.junyi.entity.Food;
import com.junyi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @time: 2021/1/26 9:46
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    private Food tmp;

    public void insert(Food food) {
        foodRepository.save(food);

        Optional<Food> redisFood = foodRepository.findById(String.valueOf(food.getId()));
        redisFood.ifPresent(value -> log.info("Find food: {}", value.toString()));
        log.info("count: {}", foodRepository.count());
    }

    public void delete(Food food) {
        foodRepository.deleteById(String.valueOf(food.getId()));
    }
}
