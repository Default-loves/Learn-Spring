package com.junyi.logging.controller;

import com.junyi.logging.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2020/10/22 16:21
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("get")
    public void getBook() {
        log.info("in com.junyi.logging.controller.BookController");
        bookService.getBook();
    }
}
