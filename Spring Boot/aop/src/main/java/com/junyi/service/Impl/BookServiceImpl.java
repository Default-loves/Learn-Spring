package com.junyi.service.Impl;

import com.junyi.bean.Book;
import com.junyi.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @time: 2020/10/19 11:12
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book getAll() {
        Book book = new Book();
        book.setId(1);
        book.setName("apple");
        return book;
    }

    @Override
    public Book badRequest() throws Exception {
        throw new Exception("bad request");
    }
}
