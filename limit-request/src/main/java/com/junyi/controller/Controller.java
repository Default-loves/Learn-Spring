package com.junyi.controller;

import com.junyi.annotation.LimitRequest;
import com.junyi.entity.Person;
import com.junyi.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2021/1/30 16:27
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
public class Controller {


    @LimitRequest(seconds = 60, maxCount = 3, needLogin = false)
    @GetMapping("/test")
    public Result test() {
        return Result.of("成功");
    }

    @PostMapping("/login")
    public void login(@RequestBody Person person) {

    }
}
