package com.junyi;

import java.util.Arrays;
import java.util.List;

/**
 * @time: 2021/2/3 18:01
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class aaa {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(list.stream().count());
    }
}
