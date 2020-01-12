package com.junyi.simple.controller.demo.controller;

import com.junyi.simple.controller.demo.controller.request.NewOrderRequest;
import com.junyi.simple.controller.demo.model.Coffee;
import com.junyi.simple.controller.demo.model.CoffeeOrder;
import com.junyi.simple.controller.demo.service.CoffeeOrderService;
import com.junyi.simple.controller.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @PostMapping("/")
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new order:{}", newOrder);
        Coffee[] coffees = coffeeService.findCoffeeByName(newOrder.getItems()).toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffees);
    }
}
