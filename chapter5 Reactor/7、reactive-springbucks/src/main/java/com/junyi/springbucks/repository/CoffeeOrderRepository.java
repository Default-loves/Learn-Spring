package com.junyi.springbucks.repository;

import com.junyi.springbucks.model.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CoffeeOrderRepository {
    @Autowired
    private DatabaseClient client;

    public Mono<Long> save(CoffeeOrder order) {
        return client.insert().into("t_order")
                .value("customer", order.getCustomer())
                .value("state", order.getState().ordinal())
                .value("create_time", new Timestamp(order.getCreateTime().getTime()))
                .value("update_time", new Timestamp(order.getUpdateTime().getTime()))
                .fetch()
                .first()
                .flatMap(c -> Mono.just((long)c.get("id")))
                .flatMap(id -> Flux.fromIterable(order.getItems())
                    .flatMap(c -> client.insert().into("t_order_coffee")
                    .value("coffee_order_id", id)
                    .value("items_id", c.getId()).then()).then(Mono.just(id)));
    }
}
