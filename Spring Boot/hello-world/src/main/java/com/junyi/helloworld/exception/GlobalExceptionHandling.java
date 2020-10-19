package com.junyi.helloworld.exception;

import com.junyi.helloworld.util.ResultEnum;
import com.junyi.helloworld.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @time: 2020/9/21 18:23
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public Response processException(CustomException e) {
        log.error("位置:{} -> 错误信息:{}", e.getMethod() ,e.getLocalizedMessage());
        return Response.createError(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    }

    /**
     * 通用异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Response exception(Exception e) {
        e.printStackTrace();
        return Response.createError(ResultEnum.UNKNOWN_EXCEPTION);
    }
}
