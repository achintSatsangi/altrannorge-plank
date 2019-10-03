package com.altran.user;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum User {
    RUBEN("#9CF196"),
    ACHINT("#ECEBA7"),
    MELISSA("#EDAAAA"),
    SAINYAM("#b1e8ed"),
    OLE("#01D28E"),
    HENRIK("#FE59D7"),
    PK("#eccd8f"),
    CAMILLA("#DA9833");

    private final String color;

    User(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static User of(String userString) {
        return Stream.of(User.values())
                .filter(user -> user.name().equals(userString))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("[" + userString + "]"));
    }
}
