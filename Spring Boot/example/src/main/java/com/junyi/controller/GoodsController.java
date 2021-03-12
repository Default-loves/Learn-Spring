package com.junyi.controller;

import com.junyi.entity.Goods;
import com.junyi.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;

/**
 * @time: 2021/3/10 16:59
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;

    @PostMapping("/getByName")
    public Goods get(String name) {
        return goodsRepository.getGoodsByNameWithQuery(name);
    }

    @GetMapping("/getByExample")
    public Goods getByExample(String name) {
        Goods good = Goods.builder()
                .name(name)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Goods> example = Example.of(good, matcher);
        return goodsRepository.findOne(example).orElse(new Goods());
    }

    @GetMapping("/getBySpecification")
    public Goods getBySpecification(String name) {
        Specification<Goods> specification = new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("name");
                return criteriaBuilder.equal(path, name);
            }
        };
        return goodsRepository.findOne(specification).orElse(new Goods());
    }

}
