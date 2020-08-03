package com.junyi.conversion.domain;

import java.util.Properties;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description:
 */
public class BookForConversion {
    private Long id;
    private String name;
    private Properties context;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "BookForConversion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", context=" + context +
                ", text='" + text + '\'' +
                '}';
    }
}
