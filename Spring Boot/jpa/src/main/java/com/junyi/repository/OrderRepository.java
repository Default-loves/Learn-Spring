package com.junyi.repository;

import com.junyi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @time: 2021/3/9 16:35
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
