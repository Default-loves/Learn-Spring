package com.junyi.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: Type接口的一些派生类或接口
 */
public class TypeDemo {
    public static void main(String[] args) {
        //原生类型 primitive type，包括int，long
        Class intClass = int.class;

        //原始类型 raw type，String
        Class stringClass = String.class;

        //数组类型 array type
        Class arrayClass = int[].class;

        Class<ArrayList> arrayListClass = ArrayList.class;
        System.out.println(arrayListClass.toString());

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println("parameterizedType: " + parameterizedType.toString());


        Type rawType = parameterizedType.getRawType();
        System.out.println("rawType: " + rawType);

        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables).forEach(System.out::println);



    }
}
