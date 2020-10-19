package com.junyi.controller;

import com.junyi.bean.Book;
import com.junyi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2020/10/19 11:10
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/find")
    public Book find(Book book) {
        return bookService.getAll();
    }

    @GetMapping("/bad")
    public Book bad() throws Exception {
        return bookService.badRequest();
    }
}
