package com.junyi.caffeine;

import com.junyi.caffeine.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @time: 2020/12/18 17:05
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class BookService {

    private static final Map<String, Book> map;

    static {
        map = new HashMap<>();
        map.put("001", new Book(1, "java"));
        map.put("002", new Book(2, "python"));
    }



    public Book getOne() {
        Book book = new Book();
        SimpleCacheManager manager = new SimpleCacheManager();

        return book;
    }

    @Cacheable("bookCache")
    public int insert(Book book) {
        map.put(book.getId().toString(), book);
        log.info("新增数据：{}", book.toString());
        return 1;
    }

    @Cacheable("bookCache")
    public Book get(String key) {
        Book book = map.get(key);
        log.info("模拟从db中获取数据：{}", book.toString());
        return book;
    }

    @CachePut("bookCache")
    public int update(Book book) {
        map.put(book.getId().toString(), book);
        log.info("修改数据：{}", book.toString());
        return 1;
    }
}
