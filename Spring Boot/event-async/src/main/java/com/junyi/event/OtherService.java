package com.junyi.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @time: 2020/9/23 15:59
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Service
@Slf4j
public class OtherService {

    public void doSomething(Book book) {
        log.info("OtherService: {}", JSON.toJSONString(book));
    }
}
