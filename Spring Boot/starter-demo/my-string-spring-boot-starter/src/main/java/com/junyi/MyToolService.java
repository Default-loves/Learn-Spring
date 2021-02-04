package com.junyi;

import java.util.List;

/**
 * @time: 2021/2/3 16:57
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class MyToolService {

    private MyConfigurationProperties properties;

    public MyToolService() {
    }

    public MyToolService(MyConfigurationProperties properties) {
        this.properties = properties;
    }

    public Integer sum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public Integer gcd(Integer a, Integer b) {
        return b == 0 ? a: gcd(b, a % b);
    }

    public String getProperties() {
        return properties.toString();
    }
}
