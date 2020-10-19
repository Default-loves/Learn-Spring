package com.junyi.service;

import com.junyi.bean.Book;

/**
 * @time: 2020/10/19 11:09
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public interface BookService {

    Book getAll();

    Book badRequest() throws Exception;
}
