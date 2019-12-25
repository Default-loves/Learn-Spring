package com.junyi.jpa.complex.demo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, Long> extends PagingAndSortingRepository<T, Long> {
    //PagingAndSortingRepository继承了CrudRepository
    List<T> findTop3ByOrderByCreateTimeDescIdAsc();
}
