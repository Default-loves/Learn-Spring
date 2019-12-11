package com.junyi.simplejdbcdemo;

import lombok.Builder;
import lombok.Data;

/**
 * User: JY
 * Date: 2019/12/9 0009
 * Description:
 */
@Data
@Builder
public class Foo {
    private int id;
    private String bar;
}
