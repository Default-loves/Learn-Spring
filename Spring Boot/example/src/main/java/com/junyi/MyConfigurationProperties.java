package com.junyi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @time: 2021/2/4 9:53
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@ConfigurationProperties(prefix = "com.junyi")
//@Component
@Data
public class MyConfigurationProperties {
    private Integer id;
    private String name;
}
