package com.junyi.helloworld.util;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体类与vo类属性之间的互转
 */
public class BeanCopierUtil {


    private static final Map<String, BeanCopier> beanCopierCache = new ConcurrentHashMap<>();

    /**
     * 把源值负值给目标值
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target){
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }


    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        T t = null;
        try{
            t = targetClass.newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            throw new RuntimeException(String.format("Create new instance of %s failed: %s", targetClass, e.getMessage()));
        }
        copyProperties(source, t);
        return t;
    }

    /**
     * 通过映射的CLASS类转换
     * @param sourceClass
     * @param targetClass
     * @return
     */
    private static BeanCopier getBeanCopier(Class sourceClass, Class targetClass) {
        String beanKey = generateKey(sourceClass, targetClass);
        BeanCopier copier = null;
        if(!beanCopierCache.containsKey(beanKey)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            beanCopierCache.put(beanKey, copier);
        }else{
            copier = beanCopierCache.get(beanKey);
        }
        return copier;
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }



}
