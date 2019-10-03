package com.altran.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class GraphData {

    private final Set<LocalDate> labels;
    private final List<DataSet> dataSets;

    public GraphData(Set<LocalDate> labels, List<DataSet> dataSets) {
        this.labels = labels;
        this.dataSets = dataSets;
    }

    public Set<LocalDate> getLabels() {
        return labels;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }
}