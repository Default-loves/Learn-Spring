package com.junyi.jpa.complex.demo.repository;

import com.junyi.jpa.complex.demo.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomer(String name);
    List<CoffeeOrder> findByItems_Name(String name);
}
