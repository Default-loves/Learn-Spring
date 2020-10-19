package com.junyi.helloworld.controller;

import com.junyi.helloworld.entity.Book;
import com.junyi.helloworld.service.BookService;
import com.junyi.helloworld.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @time: 2020/9/21 17:10
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("")
    public Response findAll() {
        return Response.createSuccess(service.findAll());
    }
}
