package com.junyi.springbucks.customer;

import com.junyi.springbucks.customer.model.Coffee;
import com.junyi.springbucks.customer.model.CoffeeOrder;
import com.junyi.springbucks.customer.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@Component
@Slf4j
public class CustomerServiceRunner implements ApplicationRunner {
    private static final URI ROOT_URI = URI.create("http://localhost:8080/");
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Link coffeeLink = getLink(ROOT_URI, "coffees");
        readCoffeeMenu(coffeeLink);
        Resource<Coffee> coffee = addCoffee(coffeeLink);

        Link orderLink = getLink(ROOT_URI, "coffeeOrders");
        addOrder(orderLink, coffee);
        queryOrders(orderLink);
    }

    private Link getLink(URI uri, String rel) {
        ResponseEntity<Resource<Link>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Link>>() {});
        Link link = response.getBody().getLink(rel);
        log.info("Link :{}", link);
        return link;
    }

    private void readCoffeeMenu(Link link) {
        ResponseEntity<PagedResources<Resource<Coffee>>> response = restTemplate.exchange(link.getTemplate().expand(), HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<Resource<Coffee>>>() {});
        log.info("Coffee Menu:{}", response.getBody());
    }
    private Resource<Coffee> addCoffee(Link link) {
        Coffee coffee = Coffee.builder()
                .name("friday")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.12))
                .build();
        RequestEntity<Coffee> req = RequestEntity.post(link.getTemplate().expand())
                .body(coffee);
        ResponseEntity<Resource<Coffee>> resp = restTemplate.exchange(req,
                new ParameterizedTypeReference<Resource<Coffee>>() {});
        log.info("Add coffee:{}", resp.getBody());
        return resp.getBody();
    }
    private void addOrder(Link link, Resource<Coffee> coffee) {
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer("junyi")
                .state(OrderState.INIT)
                .build();
        RequestEntity req = RequestEntity.post(link.getTemplate().expand())
                .body(coffeeOrder);
        ResponseEntity<Resource<CoffeeOrder>> resp = restTemplate.exchange(req,
                new ParameterizedTypeReference<Resource<CoffeeOrder>>() {});
        log.info("Add order response:{}", resp);

        Resource<CoffeeOrder> order = resp.getBody();
        Link items = order.getLink("items");
        req = RequestEntity.post(items.getTemplate().expand()).body(Collections.singletonMap("_links", coffee.getLink("self")));
        ResponseEntity<String> itemResp = restTemplate.exchange(req, String.class);
        log.info("add Order Items Response: {}", itemResp);
    }
    private void queryOrders(Link link) {
        ResponseEntity<String> resp = restTemplate.getForEntity(link.getTemplate().expand(), String.class);
        log.info("query Order Response: {}", resp);
    }

}
