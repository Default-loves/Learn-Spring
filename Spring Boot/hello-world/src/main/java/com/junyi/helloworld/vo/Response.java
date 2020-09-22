package com.junyi.helloworld.vo;

import com.junyi.helloworld.util.ResponseEnum;
import com.junyi.helloworld.util.ResultEnum;
import lombok.Data;

/**
 * @time: 2020/9/21 16:54
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
public class Response<T> {

    /** Status code */
    private Integer code;
    /** Response message */
    private String message;
    /** Response data */
    private T data;

    public Response(){}

    public Response(String msg) {
        this.message = msg;
    }

    public Response(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Integer code) {
        this.code = code;
    }

    public static <T> Response<T> createSuccess(T data) {
        return new Response<T>(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static Response createError(ResultEnum result) {
        return new Response(result.getCode(), result.getMsg());
    }
}
