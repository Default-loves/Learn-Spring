package com.junyi.caffeine.entity;

/**
 * @time: 2020/12/18 16:54
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class Book {
    private Integer id;
    private String description;

    public Book() {
    }

    public Book(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Book of(String value) {
        Book book = new Book();
        book.setDescription(value);
        return book;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
