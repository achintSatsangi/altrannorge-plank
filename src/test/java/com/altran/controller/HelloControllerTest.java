package com.altran.controller;

import org.junit.Before;
import org.junit.Test;

import static com.altran.controller.HelloController.WELCOME_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloControllerTest {

    private HelloController classToTest;

    @Before
    public void setUp() {
        classToTest = new HelloController();
    }

    @Test
    public void should_return_correct_message() {
        assertThat(classToTest.hello()).isEqualTo(WELCOME_MESSAGE);
    }
}