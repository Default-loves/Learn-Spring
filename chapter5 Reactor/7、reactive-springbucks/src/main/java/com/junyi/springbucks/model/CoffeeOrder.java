package com.junyi.springbucks.model;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//@Data
//@Entity
//@Table(name = "T_ORDER")
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class CoffeeOrder extends BaseEntity  implements Serializable {
//    private String customer;
//    @ManyToMany
//    @JoinTable(name = "T_ORDER_COFFEE")
//    @OrderBy("id")
//    private List<Coffee> items;
//    @Column(nullable = false)
//    @Enumerated
//    private OrderState state;
//}
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder implements Serializable {
    private Long id;
    private String customer;
    private OrderState state;
    private List<Coffee> items;
    private Date createTime;
    private Date updateTime;
}