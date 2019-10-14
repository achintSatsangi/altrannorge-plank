package com.altran.controller;

import com.altran.converter.PlankDataToTeamGraphDataConverter;
import com.altran.converter.PlankDataToUserGraphDataConverter;
import com.altran.dao.PlankDataDao;
import com.altran.dao.TeamDao;
import com.altran.dao.UserDao;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plank")
public class PlankDataController {

    private final PlankDataDao plankDataDao;
    private final PlankDataToUserGraphDataConverter userGraphDataConverter;
    private final PlankDataToTeamGraphDataConverter teamGraphDataConverter;
    private final UserDao userDao;
    private final TeamDao teamDao;

    @Autowired
    public PlankDataController(PlankDataDao plankDataDao, PlankDataToUserGraphDataConverter userGraphDataConverter, PlankDataToTeamGraphDataConverter teamGraphDataConverter, UserDao userDao, TeamDao teamDao) {
        this.plankDataDao = plankDataDao;
        this.userGraphDataConverter = userGraphDataConverter;
        this.teamGraphDataConverter = teamGraphDataConverter;
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @GetMapping("getData")
    public List<PlankData> getData() {
        return plankDataDao.getAllData();
    }

    /**
     * Either saves a new PlankData for a specific user and date, or if already found overwrites the data
     *
     * @param plankData
     */
    @PostMapping("postData")
    public void setData(@RequestBody PlankData plankData) {
        try {
            plankDataDao.insert(plankData);
        } catch (DuplicateKeyException e) {
            plankDataDao.updateByUserAndDate(plankData);
        }
    }

    @GetMapping("getDataForDays/{days}")
    public List<PlankData> getDataForDays(@PathVariable("days") Integer days) {
        return plankDataDao.getDataForDays(days);
    }

    @GetMapping("allUserDataForGraph")
    public GraphData userDataForGraph() {
        return userGraphDataConverter.convert(plankDataDao.getAllData(), userDao.getAllUsers());
    }

    @GetMapping("userDataForGraph/{days}")
    public GraphData userDataForGraph(@PathVariable("days") Integer days) {
        return userGraphDataConverter.convert(plankDataDao.getDataForDays(days), userDao.getAllUsers());
    }

    @GetMapping("allTeamDataForGraph")
    public GraphData teamDataForGraph() {
        return teamGraphDataConverter.convert(plankDataDao.getAllData(), userDao.getAllUsers(), teamDao.getAllTeams());
    }

    @GetMapping("teamDataForGraph/{days}")
    public GraphData teamDataForGraph(@PathVariable("days") Integer days) {
        return teamGraphDataConverter.convert(plankDataDao.getDataForDays(days), userDao.getAllUsers(), teamDao.getAllTeams());
    }
}