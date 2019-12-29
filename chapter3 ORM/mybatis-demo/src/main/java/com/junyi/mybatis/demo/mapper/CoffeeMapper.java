package com.junyi.mybatis.demo.mapper;

import com.junyi.mybatis.demo.model.Coffee;
import org.apache.ibatis.annotations.*;


@Mapper
public interface CoffeeMapper {
    @Insert("insert into t_coffee(name, price, create_time, update_time)" +
            " values(#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);    //注意返回值为数据库表影响的条数

    @Select("select * from t_coffee where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
            // map-underscore-to-camel-case = true 可以实现一样的效果
    })
    Coffee findById(@Param("id") Long id);
}
