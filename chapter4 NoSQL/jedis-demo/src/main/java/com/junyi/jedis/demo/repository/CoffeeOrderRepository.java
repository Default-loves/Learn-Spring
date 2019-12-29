package com.junyi.jedis.demo.repository;

import com.junyi.jedis.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomer(String name);
    List<CoffeeOrder> findByItems_Name(String name);
}
