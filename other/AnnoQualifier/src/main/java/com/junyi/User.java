package com.junyi;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * User: JY
 * Date: 2020/2/22 0022
 * Description:
 */
@Data
@Component
public class User {
    private String name;
    private int age;
    @Autowired
    @Qualifier("job1")
    private Job job;
}
