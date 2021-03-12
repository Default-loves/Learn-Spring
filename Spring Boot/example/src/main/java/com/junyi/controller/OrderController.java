package com.junyi.controller;

import com.junyi.entity.Order;
import com.junyi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @time: 2021/3/9 16:35
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/getAll")
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    @GetMapping("/getOne")
    public Order getOne() {
        return orderRepository.findById(1).get();
        // 不能使用下面的语句，会报错
        //orderRepository.getOne(1);
    }
    @GetMapping("/save")
    public Order save() {
        Order order = Order.builder()
                .id(10)
                .customer("xiaoming")
                .build();
        order = orderRepository.save(order);
        return order;
    }
}
