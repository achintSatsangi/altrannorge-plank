package com.altran.model;

import java.time.LocalDate;
import java.util.Objects;

public class PlankData {

    private final int id;
    private final String user;
    private final LocalDate date;
    private final int plankTimeInSeconds;

    public PlankData(int id, String user, LocalDate date, int plankTimeInSeconds) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.plankTimeInSeconds = plankTimeInSeconds;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPlankTimeInSeconds() {
        return plankTimeInSeconds;
    }

    @Override
    public String toString() {
        return "PlankData{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", plankTimeInSeconds=" + plankTimeInSeconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlankData plankData = (PlankData) o;
        return id == plankData.id &&
                plankTimeInSeconds == plankData.plankTimeInSeconds &&
                Objects.equals(user, plankData.user) &&
                Objects.equals(date, plankData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, date, plankTimeInSeconds);
    }
}
