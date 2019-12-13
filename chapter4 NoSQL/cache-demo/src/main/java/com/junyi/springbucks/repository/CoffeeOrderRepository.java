package com.junyi.springbucks.repository;


import com.junyi.springbucks.model.CoffeeOrder;

import java.util.List;


public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomer(String name);
    List<CoffeeOrder> findByItems_Name(String name);
}
