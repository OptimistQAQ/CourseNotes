package com.optimist.dao;

import com.optimist.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    //使用配置类中的JDBC模板
    private JdbcTemplate jdbcTemplate;

    /*
     * 更新方法，包括添加、删除、修改
     * param为sql中的参数，如通配符
     */
    @Override
    public int update(String sql, Object[] param) {
        return jdbcTemplate.update(sql, param);
    }

    /*
    * 查询方法
    * param为sql中的参数，如通配符
     */
    @Override
    public List<MyUser> query(String sql, Object[] param) {
        RowMapper<MyUser> rowMapper = new BeanPropertyRowMapper<MyUser>(MyUser.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
