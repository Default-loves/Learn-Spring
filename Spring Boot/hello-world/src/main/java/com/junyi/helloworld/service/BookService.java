package com.junyi.helloworld.service;

import com.junyi.helloworld.entity.Book;

import java.util.List;

/**
 * @time: 2020/9/21 17:12
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public interface BookService {

    List<Book> findAll();
}
