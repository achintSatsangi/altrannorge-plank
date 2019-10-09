package com.altran.controller;

import com.altran.dao.UserDao;
import com.altran.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDataController {

    private final UserDao userDao;

    @Autowired
    public UserDataController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return userDao.getAllUsers();
    }

}
