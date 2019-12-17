package com.junyi.thymoleaf.view.demo.repository;

import com.junyi.thymoleaf.view.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
