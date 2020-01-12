package com.junyi.more.complex.controller.demo.repository;

import com.junyi.more.complex.controller.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
