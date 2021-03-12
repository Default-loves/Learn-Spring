package com.junyi;

import com.junyi.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @time: 2021/3/11 9:00
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
@Slf4j
public class MyRunner1 implements ApplicationRunner {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("test");
        URI url = URI.create("http://localhost:12345/order/getOne");
        Order order = restTemplate.getForObject(url, Order.class);
        log.info(order.toString());
//
        url = URI.create("http://localhost:12345/order/getAll");
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());

        order = restTemplate.getForObject("http://localhost:12345/order/{id}/", Order.class, 1);
        log.info(order.toString());

        order = Order.builder()
                .id(9)
                .customer("xiaobai")
                .build();
        ResponseEntity<Order> orderResponseEntity = restTemplate.postForEntity("http://localhost:12345/order/save", order, Order.class);
        log.info(orderResponseEntity.getBody().toString());

        // exchange 综合了 post 和 get 等方法
        ResponseEntity<Order> exchange = restTemplate.exchange("http://localhost:12345/order/getOne", HttpMethod.GET, null, Order.class);
        log.info("exchange: {}", exchange.toString());

    }
}
