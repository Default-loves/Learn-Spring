package com.junyi;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.awt.print.Book;

/**
 * @time: 2021/2/3 16:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@ConfigurationProperties(prefix = "com.junyi")
public class MyConfigurationProperties {

    private Boolean enabled;
    private String name;
    private String clientId;


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
