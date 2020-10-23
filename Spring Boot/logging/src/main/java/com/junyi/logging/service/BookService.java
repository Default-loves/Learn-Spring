package com.junyi.logging.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @time: 2020/10/22 16:17
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class BookService {

    public void getBook() {
        log.debug("in com.junyi.logging.service.BookService");
        log.info("in com.junyi.logging.service.BookService");
        log.warn("in com.junyi.logging.service.BookService");
        log.error("in com.junyi.logging.service.BookService");
    }
}
