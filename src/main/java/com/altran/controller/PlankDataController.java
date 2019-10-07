package com.altran.controller;

import com.altran.converter.PlankDataToGraphDataConverter;
import com.altran.dao.PlankDataDao;
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
    private final PlankDataToGraphDataConverter converter;

    @Autowired
    public PlankDataController(PlankDataDao plankDataDao, PlankDataToGraphDataConverter converter) {
        this.plankDataDao = plankDataDao;
        this.converter = converter;
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

    @GetMapping("getAllDataForGraph")
    public GraphData getAllDataForGraph() {
        return converter.convert(plankDataDao.getAllData());
    }

    @GetMapping("getDataForGraph/{days}")
    public GraphData getDataForGraph(@PathVariable("days") Integer days) {
        return converter.convert(plankDataDao.getDataForDays(days));
    }

}