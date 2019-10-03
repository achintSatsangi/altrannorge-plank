package com.altran.model;

import com.altran.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;
import java.util.Objects;

public class PlankData {

    private final Integer id;
    private final User user;
    private final LocalDate date;
    private final int plankTimeInSeconds;

    @JsonCreator
    public PlankData(Integer id, User user, LocalDate date, int plankTimeInSeconds) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.plankTimeInSeconds = plankTimeInSeconds;
    }

    public PlankData(User user, LocalDate date, int plankTimeInSeconds) {
        this.id = null;
        this.user = user;
        this.date = date;
        this.plankTimeInSeconds = plankTimeInSeconds;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
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
        return id.equals(plankData.id) &&
                plankTimeInSeconds == plankData.plankTimeInSeconds &&
                Objects.equals(user, plankData.user) &&
                Objects.equals(date, plankData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, date, plankTimeInSeconds);
    }
}
