package com.altran.controller;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlankingDataControllerTest {
    private PlankingDataController classToTest;
    private List<String> userNames;

    @Before
    public void setUp() {
        classToTest = new PlankingDataController();
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
    public void should_return_correct_message() {
        assertThat(classToTest.getUsers()).isEqualTo(userNames);
    }
}
