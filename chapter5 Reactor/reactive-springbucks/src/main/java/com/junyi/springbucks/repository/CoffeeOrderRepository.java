package com.junyi.springbucks.repository;

import com.junyi.springbucks.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
