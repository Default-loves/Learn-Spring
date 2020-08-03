package com.junyi.generic;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.resolveReturnType;
import static org.springframework.core.GenericTypeResolver.resolveReturnTypeArgument;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: Spring泛型类型辅助类，里面还演示了泛型类型参数的具体化
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {

        // String 是 Comparable<String> 具体化，class String implements Comparable<String>
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");

        // ArrayList<Object> 是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

        // StringList 也是 List 泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        // 具备具体类型 ParameterizedType 返回类型，否则 null

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }


    public static StringList getStringList() {
        return null;
    }

    public static ArrayList<Object> getList() { // 泛型参数类型具体化
        return null;
    }

    public static String getString() {
        return null;
    }


    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);

        Class<?> returnType = resolveReturnType(method, containingClass);
        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

        // 常规类型不具备泛型参数类型 List<E>
        Class<?> returnTypeArgument = resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnTypeArgument);

    }
}
