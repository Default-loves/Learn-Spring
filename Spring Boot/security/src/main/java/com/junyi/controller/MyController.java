package com.junyi.controller;

import com.junyi.entity.Fruit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2021/3/6 11:50
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("test")
public class MyController {

    @GetMapping("getOne")
    public Fruit get() {
        return Fruit.builder().id(1)
                .name("apple")
                .build();
    }
}
