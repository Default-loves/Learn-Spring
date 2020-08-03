package com.junyi.generic;

import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: Spring泛型集合类型辅助类实例，已经被遗弃了，使用 ResolvableType
 * @see GenericCollectionTypeResolver
 * @see ResolvableType
 */
public class GenericCollectionTypeResolverDemo {
    private StringList stringList;
    private ArrayList<String> list;

    public static void main(String[] args) throws NoSuchFieldException {
        // getCollectionType()会返回具体化泛型参数类型集合的成员类型
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));;
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));;

        Field stringList = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringList));;

        Field list = GenericCollectionTypeResolverDemo.class.getDeclaredField("list");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(list));;

    }
}
