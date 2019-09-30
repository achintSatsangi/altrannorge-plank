package com.altran.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    public static String WELCOME_MESSAGE = "Hello, I'm the new plank app!!";

    @GetMapping("/")
    public String hello(){
        return WELCOME_MESSAGE;
    }
}