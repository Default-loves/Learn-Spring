package com.junyi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @time: 2021/1/30 16:37
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int status;
    private String msg;

    public static Result of(String msg){
        return new Result(200, msg);
    }

    public static Result error(String msg) {
        return new Result(404, msg);
    }
}
