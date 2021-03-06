package com.junyi.cors;

/**
 * @time: 2020/8/13 13:55
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class Book {

    private Long id;
    private String description;

    public Book(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
