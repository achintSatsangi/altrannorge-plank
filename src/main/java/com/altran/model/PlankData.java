package com.altran.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;
import java.util.Objects;

public class PlankData {

    private final Integer id;
    private final LocalDate date;
    private final int plankTimeInSeconds;
    private final Integer userId;

    @JsonCreator
    public PlankData(Integer id, LocalDate date, int plankTimeInSeconds, Integer userId) {
        this.id = id;
        this.date = date;
        this.plankTimeInSeconds = plankTimeInSeconds;
        this.userId = userId;
    }

    public PlankData(LocalDate date, int plankTimeInSeconds, Integer userId) {
        this.userId = userId;
        this.id = null;
        this.date = date;
        this.plankTimeInSeconds = plankTimeInSeconds;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPlankTimeInSeconds() {
        return plankTimeInSeconds;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "PlankData{" +
                "id=" + id +
                ", date=" + date +
                ", plankTimeInSeconds=" + plankTimeInSeconds +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlankData plankData = (PlankData) o;
        return plankTimeInSeconds == plankData.plankTimeInSeconds &&
                id.equals(plankData.id) &&
                date.equals(plankData.date) &&
                userId.equals(plankData.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, plankTimeInSeconds, userId);
    }
}
