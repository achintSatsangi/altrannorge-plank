package com.altran.user;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum User {
    RUBEN,
    ACHINT,
    MELISSA,
    SAINYAM,
    OLE,
    HENRIK,
    PK,
    CAMILLA;

    public static User of(String userString) {
        return Stream.of(User.values())
                .filter(user -> user.name().equals(userString))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("[" + userString + "]"));
    }
}
