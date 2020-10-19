package com.junyi.bean;

/**
 * @time: 2020/10/19 11:10
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */

public class Book {
    private Integer id;
    private String name;

    public Book(){}

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
