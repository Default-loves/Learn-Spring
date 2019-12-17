package com.junyi.complex.controller.demo.controller;

import com.junyi.complex.controller.demo.controller.request.NewOrderRequest;
import com.junyi.complex.controller.demo.model.Coffee;
import com.junyi.complex.controller.demo.model.CoffeeOrder;
import com.junyi.complex.controller.demo.service.CoffeeOrderService;
import com.junyi.complex.controller.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new order:{}", newOrder);
        Coffee[] coffees = coffeeService.findCoffeeByName(newOrder.getItems()).toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffees);
    }
}
