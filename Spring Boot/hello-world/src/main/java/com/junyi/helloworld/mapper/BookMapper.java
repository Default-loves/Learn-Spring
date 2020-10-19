package com.junyi.helloworld.mapper;

import com.junyi.helloworld.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}