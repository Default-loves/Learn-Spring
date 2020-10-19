package com.junyi.event;

import org.springframework.context.ApplicationEvent;

/**
 * @time: 2020/9/23 15:39
 * @version: 1.0
 * @author: junyi Xu
 * @description: Custom Event
 * @see ApplicationEvent
 */
public class BookEvent extends ApplicationEvent{

    public BookEvent(Book source) {
        super(source);
    }

    @Override
    public Book getSource() {
        return (Book) source;
    }
}
