package com.junyi.data.binding;

import com.junyi.ioc.domain.House;
import com.junyi.ioc.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;

/**
 * User: JY
 * Date: 2020/7/13 0013
 * Description: 数据绑定示例
 * @see DataBinder
 */
public class DataBinderDemo {
    public static void main(String[] args) {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("birthday", "1993");
        map.put("id", "1");
        map.put("description", "data binder");
        map.put("house", new House());
        map.put("house.name", "big house");


        //1. 默认忽略未知字段，设置false后对未知字段会报错
//        dataBinder.setIgnoreUnknownFields(false);
        //2. 默认支持嵌套属性，设置为false后会报错
//        dataBinder.setAutoGrowNestedPaths(false);
        //如果不支持嵌套属性则会报错，那么可以设置忽略掉非法字段
//        dataBinder.setIgnoreInvalidFields(true);

        MutablePropertyValues propertyValues = new MutablePropertyValues(map);

        dataBinder.setRequiredFields("id", "name"); //必须绑定的名单
//        dataBinder.setAllowedFields("id", "description"); //白名单
//        dataBinder.setDisallowedFields();   //黑名单

        dataBinder.bind(propertyValues);
        System.out.println(user);

        // 获取绑定结果，包含错误文案信息
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }
}
