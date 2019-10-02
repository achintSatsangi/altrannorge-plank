package com.altran.controller;

import com.altran.dao.PlankDataDao;
import com.altran.model.PlankData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plank")
public class PlankDataController {

    private final PlankDataDao plankDataDao;

    @Autowired
    public PlankDataController(PlankDataDao plankDataDao) {
        this.plankDataDao = plankDataDao;
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

}