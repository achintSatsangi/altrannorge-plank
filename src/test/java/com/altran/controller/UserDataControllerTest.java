package com.altran.controller;

import com.altran.dao.UserDao;
import com.altran.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserDataControllerTest {
    private UserDataController classToTest;
    private List<User> users;
    private UserDao mockUserDao;

    @BeforeEach
    void setUp() {
        mockUserDao = Mockito.mock(UserDao.class);
        classToTest = new UserDataController(mockUserDao);
        users = List.of(new User(2, "ACHINT", "Achint", null), new User(2, "RUBEN", "Ruben", null));
    }

    @Test
    void should_return_correct_list_as_userDTO_objects() {
        when(mockUserDao.getAllUsers()).thenReturn(users);
        assertThat(classToTest.getUsers()).hasSize(2)
                .extracting("username")
                .containsExactly("ACHINT", "RUBEN");
    }


}
