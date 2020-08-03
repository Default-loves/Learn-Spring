package com.junyi.enent.customized;

import org.springframework.context.ApplicationEvent;

/**
 * User: JY
 * Date: 2020/7/15 0015
 * Description: 自定义Spring事件
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(String source) {
        super(source);
    }

    @Override
    public String getSource() {
        return (String) source;
    }

}
