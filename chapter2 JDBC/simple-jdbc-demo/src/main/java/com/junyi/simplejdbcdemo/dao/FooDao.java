package com.junyi.simplejdbcdemo.dao;

import com.junyi.simplejdbcdemo.domain.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: JY
 * Date: 2019/12/9 0009
 * Description:
 */
@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insert() {
        // use jdbcTemplate to insert
        Arrays.asList("a", "b").forEach(bar -> {
            jdbcTemplate.update("insert into FOO(Bar) values (?)", bar);
        });

        // use simpleJdbcInsert to insert
        Map<String, String> map = new HashMap<>();
        map.put("bar", "abc");
        Number k = simpleJdbcInsert.executeAndReturnKey(map);
        log.info("ID of abc is : {}", k.intValue());
    }

    public void listData() {
        log.info("Count: {}", jdbcTemplate.queryForObject("select count(*) from FOO", Long.class));
        List<String> set = jdbcTemplate.queryForList("select Bar from FOO", String.class);
        set.forEach(bar -> log.info("Bar: {}", bar));
        List<Foo> lst = jdbcTemplate.query("select * from FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .id(resultSet.getInt(1))
                        .bar(resultSet.getString(2))
                        .build();
            }
        });
        lst.forEach(foo -> log.info("Foo : {}", foo));
    }
}
