package com.altran.dao;

import com.altran.model.PlankData;
import com.altran.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

@Repository
public class PlankDataDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PlankDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PlankData> getAllData() {
        return jdbcTemplate.query("SELECT * FROM plank_data order by date", new PlankDataRowMapper());
    }

    public List<PlankData> getDataForDays(int days) {
        return jdbcTemplate.query("SELECT * FROM plank_data where date >= ?  order by date", new Object[]{now().minusDays(days)}, new PlankDataRowMapper());
    }

    public PlankData getDataById(Integer id) {
        return (PlankData) jdbcTemplate.queryForObject("SELECT * FROM plank_data Where id=?", new Object[]{id}, new PlankDataRowMapper());
    }

    public PlankData insert(PlankData plankData) {
        jdbcTemplate.update("insert into plank_data (username, date, plank_time) values ( ?, ?, ?)", plankData.getUser().name(), plankData.getDate(), plankData.getPlankTimeInSeconds());
        return plankData;
    }

    /**
     * Insert a record with a non auto-generated ID
     * @param plankData
     * @return PlankData
     */
    public PlankData insertById(PlankData plankData) {
        jdbcTemplate.update("insert into plank_data (id, username, date, plank_time) values (?, ?, ?, ?)",plankData.getId(), plankData.getUser().name(), plankData.getDate(), plankData.getPlankTimeInSeconds());
        return plankData;
    }

    public PlankData updateById(PlankData plankData) {
        jdbcTemplate.update("update plank_data set username=?, date=?, plank_time=? where id=?", plankData.getUser().name(), plankData.getDate(), plankData.getPlankTimeInSeconds(), plankData.getId());
        return plankData;
    }

    public PlankData updateByUserAndDate(PlankData plankData) {
        jdbcTemplate.update("update plank_data set plank_time=? where username=? and date=?", plankData.getPlankTimeInSeconds(), plankData.getUser().name(), plankData.getDate());
        return plankData;
    }

    /**
     * Gets a record based on the username and date
     * @param username String username to look for
     * @param date date to look for
     * @return PlankData
     */
    public PlankData getByUserAndDate(User username, LocalDate date) {
        return (PlankData) jdbcTemplate.queryForObject("SELECT * FROM plank_data Where username=? AND date=?", new Object[]{username.name(), date}, new PlankDataRowMapper());
    }

    public boolean delete(Integer id) {
        return jdbcTemplate.update("delete from plank_data where id=?", id) == 1;
    }

    public void deleteAll() {
        jdbcTemplate.execute("delete from plank_data");
    }

    public class PlankDataRowMapper implements RowMapper
    {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PlankData(rs.getInt("id"), User.of(rs.getString("username")), rs.getDate("date").toLocalDate(), rs.getInt("plank_time"));
        }
    }
}
