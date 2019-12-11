package com.junyi.jpa.demo.repository;

import com.junyi.jpa.demo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
