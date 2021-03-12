package com.junyi.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @time: 2021/3/8 13:52
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */

@Service
public class MetricsService {

    @Autowired
    MeterRegistry meterRegistry;

    public void moniter() {
        meterRegistry.counter("my-counter").increment();
        meterRegistry.summary("com.junyi.order.number").record(1);
    }
}
