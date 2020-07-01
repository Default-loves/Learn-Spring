package com.junyi.springbucks.service;


import com.junyi.springbucks.model.CoffeeOrder;
import com.junyi.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public Mono<Long> createOrder(CoffeeOrder order) {
        return orderRepository.save(order);
    }

}
