package com.altran.dao;

import com.altran.user.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeamDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Team> getAllTeams() {
        return jdbcTemplate.query("SELECT * FROM teams", new TeamDao.TeamMapper());
    }

    public class TeamMapper implements RowMapper
    {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Team(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
        }
    }
}
