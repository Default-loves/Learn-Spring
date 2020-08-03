package com.junyi.generic;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: ResolvableType实例
 * @see ResolvableType
 */
public class ResolvableTypeDemo {
    private Map<Integer, List<String>> myMap = new HashMap<>();

    public static void main(String[] args) throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
        t.getSuperType(); // AbstractMap<Integer, List<String>>;
        t.asMap(); // Map<Integer, List<String>>;
        t.getGeneric(0).resolve(); // Integer
        t.getGeneric(1).resolve(); // List
        t.getGeneric(1); // List<String>;
        t.resolveGeneric(1, 0); // String


        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        // StringList - ArrayList - AbstractList - List - Collection
        System.out.println(resolvableType.getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType());
        System.out.println(resolvableType.asCollection().resolve());
        System.out.println(resolvableType.asCollection().resolveGeneric(0));
    }
}
