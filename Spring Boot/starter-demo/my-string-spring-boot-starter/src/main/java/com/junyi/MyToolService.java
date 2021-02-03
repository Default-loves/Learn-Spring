package com.junyi;

import java.util.List;

/**
 * @time: 2021/2/3 16:57
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class MyToolService {

    private String string;

    public MyToolService() {
    }

    public Long sum(List<Integer> list) {
        return list.stream().count();
    }

    public Integer gcd(Integer a, Integer b) {
        return b == 0 ? a: gcd(b, a % b);
    }
}
