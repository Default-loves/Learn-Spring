package com.junyi.springbucks.customer;

import com.junyi.springbucks.customer.model.Coffee;
import com.junyi.springbucks.customer.model.CoffeeOrder;
import com.junyi.springbucks.customer.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomRunner implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        listMenu();
        long id = orderCoffee();
        queryOrder(id);
    }
    private void listMenu() {
        ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> res = restTemplate.exchange("http://localhost:8080/coffee/", HttpMethod.GET, null, ptr);
        res.getBody().forEach(c -> log.info("Coffee:{}", c));

    }
    private long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .name("junyi")
                .items(Arrays.asList("mocha", "latte"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString("http://localhost:8080/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<CoffeeOrder> response = restTemplate.exchange(request, CoffeeOrder.class);
        log.info("Response code:{}", response.getStatusCode());
        long id = response.getBody().getId();
        log.info("Order id:{}", id);
        return id;
    }
    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate.getForObject("http://localhost:8080/order/{id}", CoffeeOrder.class, id);
        log.info("Query Order:{}", order);
    }
}
