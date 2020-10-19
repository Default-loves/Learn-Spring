package com.junyi.event;

/**
 * @time: 2020/9/23 15:41
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class Book {
    private Integer id;
    private String name;

    public Book() {}

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
