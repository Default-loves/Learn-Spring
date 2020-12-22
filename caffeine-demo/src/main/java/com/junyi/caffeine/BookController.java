package com.junyi.caffeine;

import com.junyi.caffeine.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @time: 2020/12/21 15:54
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("insert")
    public String insert(@RequestBody Book book) {
        bookService.insert(book);
        return "insert success";
    }

    @GetMapping("get")
    public Book getBook(@RequestParam("key") String key) {
        Book book = bookService.get(key);
        return book;
    }

    @PostMapping("update")
    public String update(@RequestBody Book book) {
        bookService.update(book);
        return "update success";
    }

    @GetMapping("cacheStat")
    public void cacheStat() {
    }
}
