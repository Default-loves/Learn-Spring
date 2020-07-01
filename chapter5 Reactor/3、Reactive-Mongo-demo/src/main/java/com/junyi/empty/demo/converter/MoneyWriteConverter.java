package com.junyi.empty.demo.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * User: JY
 * Date: 2019/12/15 0015
 * Description:
 */
public class MoneyWriteConverter implements Converter<Money, Long> {
    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}
