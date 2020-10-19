package com.junyi.event;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @time: 2020/9/23 15:57
 * @version: 1.0
 * @author: junyi Xu
 * @description: Custom Event listener, handle event
 */
@Component
@Slf4j
public class BookListener implements ApplicationListener<BookEvent> {

    @Autowired
    OtherService otherService;


    @Override
    @Async
    public void onApplicationEvent(BookEvent event) {
        Book book = event.getSource();
        log.info("Catch Book Event: {}", JSON.toJSONString(book));
        otherService.doSomething(book);
    }
}

