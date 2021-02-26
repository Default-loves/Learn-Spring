package com.junyi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2021/2/25 15:04
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("test")
@Slf4j
public class myController {

    @GetMapping("/get")
    public String get() {
        log.info("test");
        return "OK";
    }
}
