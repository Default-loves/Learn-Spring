package com.junyi.entity;


import lombok.Builder;
import lombok.Data;

/**
 * @time: 2021/1/30 16:28
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
@Builder
public class Person {
    private Integer id;
    private String name;
    private String password;
}
