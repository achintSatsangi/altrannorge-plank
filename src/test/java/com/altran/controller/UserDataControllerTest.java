package com.altran.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDataControllerTest {
    private UserDataController classToTest;
    private List<String> userNames;

    @BeforeEach
    public void setUp() {
        classToTest = new UserDataController();
        userNames = new ArrayList<>();
        userNames.add("RUBEN");
        userNames.add("ACHINT");
        userNames.add("MELISSA");
        userNames.add("SAINYAM");
        userNames.add("OLE");
        userNames.add("HENRIK");
        userNames.add("PK");
        userNames.add("CAMILLA");
    }

    @Test
    public void should_return_correct_list() {
        assertThat(classToTest.getUsers()).isEqualTo(userNames);
    }
}
