package com.junyi.helloworld.util;

/**
 * @time: 2020/9/21 17:32
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public enum ResponseEnum {
    SUCCESS(200, "SUCCESS"),
    FAILED(404, "FAILED");

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
