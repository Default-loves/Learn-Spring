package com.junyi.dependency.domain;

/**
 * User: JY
 * Date: 2020/7/3 0003
 * Description:
 */
public class Book {
    private Integer id;

    private String description;

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
