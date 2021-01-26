package com.junyi.abc.service;

import com.junyi.abc.entity.Food;
import com.junyi.abc.mapper.FoodMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    FoodMapper foodMapper;

    public Food getOne(Integer id) {
        return foodMapper.getById(id);
    }

    public List<Food> listAll() {
        return foodMapper.lists();
    }

    @Transactional
    public void test(Integer count) {
        Food food = foodMapper.getById(1);
        log.info("id:{}, count:{}", food.getId(), food.getCount());
        food.setCount(count);
        foodMapper.update(food);
    }
}
