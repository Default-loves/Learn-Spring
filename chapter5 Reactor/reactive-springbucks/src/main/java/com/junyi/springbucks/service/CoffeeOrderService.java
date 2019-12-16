package com.junyi.springbucks.service;


import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.model.CoffeeOrder;
import com.junyi.springbucks.model.OrderState;
import com.junyi.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
//
//@Service
//@Slf4j
//@Transactional
//public class CoffeeOrderService {
//    @Autowired
//    private CoffeeOrderRepository orderRepository;
//
//    public CoffeeOrder createOrder(String custom, Coffee...coffees) {
//        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
//                .customer(custom)
//                .items(new ArrayList<>(Arrays.asList(coffees)))
//                .state(OrderState.INIT)
//                .build();
//        CoffeeOrder saved = orderRepository.save(coffeeOrder);
//        log.info("Create order:{}", saved);
//        return saved;
//    }
//
//    public boolean updateState(CoffeeOrder coffeeOrder, OrderState state) {
//        if (state.compareTo(coffeeOrder.getState()) <= 0) {
//            log.info("Warning! Can't update {};{}", state, coffeeOrder.getState());
//            return false;
//        }
//        coffeeOrder.setState(state);
//        orderRepository.save(coffeeOrder);
//        log.info("Update order:{}", coffeeOrder);
//        return true;
//    }
//}

@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New Order: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
