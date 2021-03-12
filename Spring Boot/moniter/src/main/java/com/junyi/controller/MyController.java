package com.junyi.controller;

import com.junyi.metrics.MetricsService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2021/3/8 10:53
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("test")
public class MyController {

    @Autowired
    MetricsService metricsService;

    @GetMapping("get")
    public String test() {
        metricsService.moniter();
        return "OK";
    }
}
