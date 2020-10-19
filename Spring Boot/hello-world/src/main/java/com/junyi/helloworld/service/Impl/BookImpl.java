package com.junyi.helloworld.service.Impl;

import com.junyi.helloworld.entity.Book;
import com.junyi.helloworld.mapper.BookMapper;
import com.junyi.helloworld.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @time: 2020/9/21 17:12
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.selectAll();
    }
}
