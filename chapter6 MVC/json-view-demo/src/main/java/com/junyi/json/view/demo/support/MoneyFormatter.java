package com.junyi.json.view.demo.support;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * 处理类似 “CNY 20.00” 的字符串
 */


@Component
public class MoneyFormatter implements Formatter<Money> {
    @Override
    public Money parse(String s, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(s)){
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(s));
        } else if (StringUtils.isNotEmpty(s)){
            String[] arr = StringUtils.split(s, " ");
            if (arr != null & arr.length == 2 && NumberUtils.isParsable(arr[1])) {
                return Money.of(CurrencyUnit.of(arr[0]), NumberUtils.createBigDecimal(arr[1]));
            } else {
                throw new ParseException(s, 0);
            }
        }
       throw new ParseException(s, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        if (money == null) {
            return null;
        }
        return money.getCurrencyUnit().getCode() + money.getAmount();
    }
}
