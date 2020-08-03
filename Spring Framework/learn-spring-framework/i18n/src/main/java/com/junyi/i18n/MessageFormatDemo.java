package com.junyi.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User: JY
 * Date: 2020/7/12 0012
 * Description: MessageFormat 示例
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String pattern = "At {1,time, long} on {1,date, full}, there was {2} on planet {0,number,integer}.";
        String result = "";

        MessageFormat messageFormat = new MessageFormat(pattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //重置Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern(pattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置pattern
        messageFormat.applyPattern("Today is {0}, {1}, {2}");
        result = messageFormat.format(new Object[]{"Sunday", "666"});
        System.out.println(result);

        //重置Format，根据参数索引来设置Pattern
        messageFormat.applyPattern(pattern);
        messageFormat.setFormat(1, new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
