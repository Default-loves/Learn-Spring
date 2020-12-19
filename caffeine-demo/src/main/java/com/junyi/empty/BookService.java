package com.junyi.empty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @time: 2020/12/18 17:05
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class BookService {


    public Book findOne() {
        return new Book();
    }
}
