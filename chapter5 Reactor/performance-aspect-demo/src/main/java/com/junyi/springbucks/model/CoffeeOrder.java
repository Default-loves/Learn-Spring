package com.junyi.springbucks.model;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity
@Table(name = "T_ORDER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
