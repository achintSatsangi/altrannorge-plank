package com.altran.user;

public class User {

    private final int id;
    private final String username;
    private final String visibleName;
    private final Integer teamId;

    public User(int id, String username, String visibleName, Integer teamId) {
        this.id = id;
        this.username = username;
        this.visibleName = visibleName;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", visibleName='" + visibleName + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
