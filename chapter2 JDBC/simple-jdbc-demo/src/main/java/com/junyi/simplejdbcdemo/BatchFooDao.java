package com.junyi.simplejdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: JY
 * Date: 2019/12/9 0009
 * Description:
 */


@Repository
public class BatchFooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insert() {
        jdbcTemplate.batchUpdate("insert into FOO(ID, BAR) values (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "b-" + i);
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });

        List<Foo> lst = new ArrayList<>();
        lst.add(Foo.builder().id(100).bar("b-100").build());
        lst.add(Foo.builder().id(101).bar("b-101").build());
        namedParameterJdbcTemplate.batchUpdate("insert into FOO(ID, BAR) values (:id, :bar)", SqlParameterSourceUtils.createBatch(lst));

    }
}
