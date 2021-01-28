package com.junyi.controller;

import com.junyi.entity.Food;
import com.junyi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @time: 2021/1/26 9:49
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping("/getOne")
    public Food getOne(@RequestParam Integer id) {
        return foodService.getOne(id);
    }

    @GetMapping("/getAll")
    public List<Food> getAll() {
        return foodService.listAll();
    }

    @GetMapping("/test")
    public String test() {
        Food food = Food.builder()
                .id(3)
                .name("bear")
                .count(100)
                .build();
        foodService.test(food);
        return "OK";
    }

    @GetMapping("/test2")
    public String test2() {
        foodService.testTemplate();
        return "OK";
    }
}
