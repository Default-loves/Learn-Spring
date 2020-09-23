package com.junyi.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time: 2020/9/23 15:39
 * @version: 1.0
 * @author: junyi Xu
 * @description: 异步事件调用
 */
@SpringBootApplication
@RestController
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public Book book() throws InterruptedException {
        Book book = new Book(1, "apple");
        bookService.save(book);
        return book;
    }
}
