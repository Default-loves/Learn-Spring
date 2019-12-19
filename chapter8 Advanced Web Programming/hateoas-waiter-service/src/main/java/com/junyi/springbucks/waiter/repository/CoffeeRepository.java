package com.junyi.springbucks.waiter.repository;

import com.junyi.springbucks.waiter.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(path = "/coffee")
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Coffee findByName(String name);
    List<Coffee> findByNameInOrderById(List<String> name);
}
