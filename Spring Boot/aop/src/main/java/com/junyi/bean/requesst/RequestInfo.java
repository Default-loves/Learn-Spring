package com.junyi.bean.requesst;

import lombok.Data;

/**
 * @time: 2020/10/19 11:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Data
public class RequestInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;

}
