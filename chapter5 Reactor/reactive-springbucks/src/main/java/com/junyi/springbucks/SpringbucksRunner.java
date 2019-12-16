package com.junyi.springbucks;

import com.junyi.springbucks.model.Coffee;
import com.junyi.springbucks.model.CoffeeOrder;
import com.junyi.springbucks.model.OrderState;
import com.junyi.springbucks.service.CoffeeOrderService;
import com.junyi.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * User: JY
 * Date: 2019/12/16 0016
 * Description:
 */
@Slf4j
@Component
public class SpringbucksRunner implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        coffeeService.initCache().then(
                coffeeService.findOneCoffee("mocha")
                .flatMap(c -> {
                    CoffeeOrder order = createOrder("junyi", c);
                    return orderService.createOrder(order);
                }).doOnError(e -> log.info("error:{}", e))
        ).subscribe(o -> log.info("Create order:{}", o));
        log.info("After subscribe");
        Thread.sleep(3000);

    }
    private CoffeeOrder createOrder(String customer, Coffee... coffee) {
        return CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffee))
                .state(OrderState.INIT)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
