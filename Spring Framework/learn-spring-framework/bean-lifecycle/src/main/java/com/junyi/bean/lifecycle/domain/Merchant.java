package com.junyi.bean.lifecycle.domain;

import java.util.List;

/**
 * User: JY
 * Date: 2020/7/10 0010
 * Description: 商家
 */
public class Merchant {
    private Long id;
    private List<Book> bookList;

    public Merchant() {
    }

    public Merchant(Long id, List<Book> bookList) {
        this.id = id;
        this.bookList = bookList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", bookList=" + bookList +
                '}';
    }
}
