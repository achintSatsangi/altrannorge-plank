package com.altran.controller;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static com.altran.controller.HelloController.WELCOME_MESSAGE;

public class HelloControllerTest {

    HelloController classToTest;

    @Before
    public void setUp() throws Exception {
        classToTest = new HelloController();
    }

    @Test
    public void should_return_correct_message() {
        Assertions.assertThat(classToTest.hello()).isEqualTo(WELCOME_MESSAGE);
    }
}