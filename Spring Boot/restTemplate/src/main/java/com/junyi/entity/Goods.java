package com.junyi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @time: 2021/3/10 16:28
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
