package com.junyi.empty.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * User: JY
 * Date: 2019/12/15 0015
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("t_coffee")
public class Coffee {
    @Id
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
