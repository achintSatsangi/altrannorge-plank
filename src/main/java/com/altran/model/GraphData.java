package com.altran.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class GraphData {

    private final List<LocalDate> labels;

    @JsonProperty("datasets")
    private final List<DataSet> dataSets;

    public GraphData(List<LocalDate> labels, List<DataSet> dataSets) {
        this.labels = labels;
        this.dataSets = dataSets;
    }

    public List<LocalDate> getLabels() {
        return labels;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }
}