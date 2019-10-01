package com.altran.controller;

import com.altran.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PlankingDataController {

    /**
     * Method for getting as list of users, currently hardcoded in an ENUM
     *
     * @return string list of all users
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<String> getUsers() {
        return Stream.of(User.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
