package com.junyi.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @time: 2021/3/6 11:52
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
@Builder
public class Fruit {
    private Integer id;
    private String name;
}
