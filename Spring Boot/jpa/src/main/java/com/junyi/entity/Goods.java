package com.junyi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @time: 2021/3/10 16:28
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Table(name = "goods")
@Entity
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
