package com.junyi;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.awt.print.Book;

/**
 * @time: 2021/2/3 16:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@ConfigurationProperties(prefix = "com.junyi")
@Data
@ToString
public class MyConfigurationProperties {

    private Boolean enable;
    private String name;
    private String clientId;
}
