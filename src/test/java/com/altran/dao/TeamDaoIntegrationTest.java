package com.altran.dao;

import com.altran.Application;
import com.altran.user.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class TeamDaoIntegrationTest {

    @Autowired
    private TeamDao teamDao;

    @Test
    void should_get_all_users() {
        List<Team> result = teamDao.getAllTeams();
        assertThat(result).hasSize(2)
                .extracting("id", "name", "location")
                .contains(tuple(1, "Norge 1", "Oslo"), tuple(2, "Norge 2", "Oslo"));
    }
}