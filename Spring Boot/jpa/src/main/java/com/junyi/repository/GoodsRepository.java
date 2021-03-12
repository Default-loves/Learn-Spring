package com.junyi.repository;

import com.junyi.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * @time: 2021/3/9 16:35
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer>
        , QueryByExampleExecutor<Goods>, JpaSpecificationExecutor<Goods> {

    @Query("select g from Goods g where g.name=?1")
    public Goods getGoodsByNameWithQuery(String name);

    public Goods getGoodsByIdAndName(Integer id, String name);
}
