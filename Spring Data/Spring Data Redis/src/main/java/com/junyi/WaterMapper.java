package com.junyi;

import com.junyi.entity.Water;
import org.apache.ibatis.annotations.Mapper;

/**
 * @time: 2021/1/16 14:36
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Mapper
public interface WaterMapper {

    int updateByPrimaryKeySelective(Water water);

    Water selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);
}
