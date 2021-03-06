package com.junyi.springbucks.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

/**
 * 为了让CoffeeCacheRepository能够处理Money类型
 */
@WritingConverter
public class MoneyToByteConverter implements Converter<Money, byte[]> {
    @Override
    public byte[] convert(Money money) {
        String price = Long.toString(money.getAmountMinorLong());
        return price.getBytes(StandardCharsets.UTF_8);
    }
}
