package com.junyi.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @time: 2021/3/8 11:00
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Configuration
@Endpoint(id = "mysystem", enableByDefault=true)
public class MyEndPoint {
    @ReadOperation
    public Map<String, Object> getMySystemInfo() {
        Map<String,Object> result= new HashMap<>();
        Map<String, String> map = System.getenv();
        result.put("username",map.get("USERNAME"));
        result.put("computername",map.get("COMPUTERNAME"));
        return result;
    }

    @ReadOperation
    public Map<String, Object> getMySystemInfoWithParam(@Selector String arg0) {
        Map<String, Object> result = new HashMap<>();
        result.put(arg0, "data from DB");
        return result;
    }
}
