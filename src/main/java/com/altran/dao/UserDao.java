package com.altran.dao;

import com.altran.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDTO> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new UserDao.UserMapper());
    }

    public class UserMapper implements RowMapper
    {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserDTO(rs.getInt("id"), rs.getString("username"), rs.getString("visible_name"), rs.getInt("team_id"));
        }
    }
}
