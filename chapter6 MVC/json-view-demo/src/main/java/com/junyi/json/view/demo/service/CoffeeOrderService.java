package com.junyi.json.view.demo.service;

import com.junyi.json.view.demo.model.Coffee;
import com.junyi.json.view.demo.model.CoffeeOrder;
import com.junyi.json.view.demo.model.OrderState;
import com.junyi.json.view.demo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder getOrderById(Long id) {
        return orderRepository.getOne(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder order1 = orderRepository.save(order);
        log.info("New order:{}", order1);
        return order1;
    }
    public boolean updateOrder(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.info("Warning! {};{}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Update from {} to {} ", order.getState(), state);
        return true;
    }
}
