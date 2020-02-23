package com.junyi.mysql.demo.mysql.demo.repositories;

import com.junyi.mysql.demo.mysql.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
