package com.junyi.authentication.customize;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @time: 2021/3/6 15:25
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Repository
public interface UserRepository extends CrudRepository<MyUserDetails, Long> {
    MyUserDetails findByUsername(String username);
}
