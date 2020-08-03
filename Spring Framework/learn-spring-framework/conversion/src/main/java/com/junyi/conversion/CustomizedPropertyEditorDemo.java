package com.junyi.conversion;

import com.junyi.conversion.domain.BookForConversion;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: 自定义PropertyEditor的实例
 * @see CustomizedPropertyEditorRegistrar
 * @see StringToPropertiesPropertyEditor
 */
public class CustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:\\META-INF\\book-for-conversion.xml");
        BookForConversion book = applicationContext.getBean("book", BookForConversion.class);

        System.out.println(book);
    }
}
