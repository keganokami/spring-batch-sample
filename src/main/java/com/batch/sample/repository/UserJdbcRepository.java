package com.batch.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository {
    @Autowired
    private JdbcTemplate template;

    private static final String EXISTS_CHECK_SQL = "" +
    "SELECT exists (SELECT * FROM sample_user where id =?)";

    public boolean exists(Integer id) {
        return template.queryForObject(EXISTS_CHECK_SQL,Boolean.class, id);
    }
}
