package com.junyi.caffeine.entity;

/**
 * @time: 2020/12/21 16:14
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class CacheBean {
    private String key;
    private long ttl;
    private long maximumSize;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }
}
