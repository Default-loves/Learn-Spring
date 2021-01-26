package com.junyi.abc.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @time: 2021/1/26 9:45
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
public class Food {
    private Integer id;
    private String name;
    private Integer count;
}
