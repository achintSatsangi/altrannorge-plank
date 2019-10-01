package com.altran.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.altran.controller.HelloController.WELCOME_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {

    private HelloController classToTest;

    @BeforeEach
    void setUp() {
        classToTest = new HelloController();
    }

    @Test
    void should_return_correct_message() {
        assertThat(classToTest.hello()).isEqualTo(WELCOME_MESSAGE);
    }
}