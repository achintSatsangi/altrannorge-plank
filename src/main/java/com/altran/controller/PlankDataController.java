package com.altran.controller;

import com.altran.dao.PlankDataDao;
import com.altran.model.PlankData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getDataForDays/{days}")
    public List<PlankData> getDataForDays(@PathVariable("days") Integer days) {
        return plankDataDao.getDataForDays(days);
    }

}