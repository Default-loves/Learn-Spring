package com.junyi.event;

/**
 * @time: 2020/9/23 15:43
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public interface BookService {

    void save(Book book) throws InterruptedException;
}
