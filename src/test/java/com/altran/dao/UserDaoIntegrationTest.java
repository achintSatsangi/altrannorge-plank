package com.altran.dao;

import com.altran.Application;
import com.altran.user.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class UserDaoIntegrationTest {

    @Autowired
    private UserDao userDao;

    @Test
    void should_get_all_users() {
        List<UserDTO> result = userDao.getAllUsers();
        assertThat(result).hasSize(8)
                    .extracting("username")
                    .contains("ACHINT", "MELISSA", "RUBEN", "OLE", "PK", "CAMILLA", "SAINYAM", "HENRIK");
    }


}