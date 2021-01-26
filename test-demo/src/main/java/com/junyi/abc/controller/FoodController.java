package com.junyi.abc.controller;

import com.junyi.abc.entity.Food;
import com.junyi.abc.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        for (int i = 0; i < 20; i++) {
            int a = i;
            new Thread(() -> foodService.test(a)).start();
        }
        return "OK";
    }
}
