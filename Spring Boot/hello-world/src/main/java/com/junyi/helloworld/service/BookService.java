package com.junyi.helloworld.service;

import com.junyi.helloworld.entity.Book;
import com.junyi.helloworld.vo.BookVO;
import com.junyi.helloworld.vo.Response;

import java.util.List;

/**
 * @time: 2020/9/21 17:12
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public interface BookService extends BaseService<BookVO, Response>{

    List<Book> findAll();
}
