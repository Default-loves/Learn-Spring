package com.junyi.helloworld.entity;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Integer id;

    private String description;

    private String name;

    private Date createDateTime;

    private Integer authorId;

    private static final long serialVersionUID = 1L;

    public Book() {
    }

    public Book(String description, String name, Date createDateTime, Integer authorId) {
        this.description = description;
        this.name = name;
        this.createDateTime = createDateTime;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

}