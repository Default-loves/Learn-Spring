package com.junyi.mapper;

import com.junyi.entity.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @time: 2021/1/26 9:47
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Mapper
public interface FoodMapper {

    Food getById(Integer id);

    List<Food> lists();


    void update(Food food);
}
