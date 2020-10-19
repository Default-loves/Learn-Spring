package com.junyi.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @time: 2020/9/23 15:42
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService{

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Book book) throws InterruptedException {
        // save data into DB with 1s
        Thread.sleep(1000);
        log.info("Save data finish: {}", JSON.toJSONString(book));
        publisher.publishEvent(new BookEvent(book));
    }
}

