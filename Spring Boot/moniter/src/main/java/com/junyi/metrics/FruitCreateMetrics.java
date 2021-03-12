package com.junyi.metrics;

import com.junyi.entity.Fruit;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * 这个类能够实现在通过 Repository 创建 Fruit 实体 后自动执行 onAfterCreate 方法
 * @time: 2021/3/8 13:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Component
public class FruitCreateMetrics extends AbstractRepositoryEventListener<Fruit> {

    private MeterRegistry meterRegistry;

    public FruitCreateMetrics() {
    }

    public FruitCreateMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    protected void onAfterCreate(Fruit entity) {
        meterRegistry.summary("com.junyi.fruit.auto.increase").record(1);
    }
}
