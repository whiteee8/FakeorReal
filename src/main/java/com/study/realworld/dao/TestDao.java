package com.study.realworld.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getName(int id) throws Exception {
        return jdbcTemplate.queryForMap(" select username, point from user where id = ?", id);
    }

    public Map<String, Object> getRank(int id) throws Exception {
        return jdbcTemplate.queryForMap(" select username, point, rank from user where id = ?", id);
    }

}
