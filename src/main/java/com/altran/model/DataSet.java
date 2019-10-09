package com.altran.model;

import com.altran.user.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"label", "fill","lineTension", "pointBorderWidth", "spanGaps", "data"})
public class DataSet {
    private final User user;
    private final boolean fill = false;
    private final double lineTension = 0.1;
    private final Integer pointBorderWidth = 1;
    private final boolean spanGaps = true;
    private final List<Integer> data;

    public DataSet(User user, List<Integer> time) {
        this.user = user;
        this.data = time;
    }

    public User getUser() {
        return user;
    }

    public String getLabel() {
        return user.name();
    }

    public boolean isFill() {
        return fill;
    }

    public double getLineTension() {
        return lineTension;
    }

    public Integer getPointBorderWidth() {
        return pointBorderWidth;
    }

    public boolean isSpanGaps() {
        return spanGaps;
    }

    public List<Integer> getData() {
        return data;
    }
}
