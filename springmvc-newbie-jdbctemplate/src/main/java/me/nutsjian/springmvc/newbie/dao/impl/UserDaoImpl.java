package me.nutsjian.springmvc.newbie.dao.impl;

import me.nutsjian.springmvc.newbie.dao.UserDao;
import me.nutsjian.springmvc.newbie.dto.UserDTO;
import me.nutsjian.springmvc.newbie.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<UserDTO> findAll() {
        this.getJdbcTemplate().query("select * from user", new UserRowMapper());
        return null;
    }
}
