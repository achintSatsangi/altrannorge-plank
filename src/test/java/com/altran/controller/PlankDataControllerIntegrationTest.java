package com.altran.controller;

import com.altran.dao.PlankDataDao;
import com.altran.model.PlankData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlankDataControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PlankDataDao plankDataDao;

    @BeforeEach
    void setup() {
        plankDataDao.insert(new PlankData(1, "ACHINT", now(), 100));
        plankDataDao.insert(new PlankData(2, "RUBEN", now().minusDays(5), 500));
    }

    @AfterEach
    void destroy() {
        plankDataDao.deleteAll();
    }

    @Test
    void should_return_all_data() {
        List<PlankData> result = this.restTemplate.getForObject("http://localhost:" + port + "/plank/getData",
                List.class);
        assertThat(result).hasSize(2)
                .extracting("user", "date")
                .containsExactly(tuple("ACHINT", now().toString()), tuple("RUBEN", now().minusDays(5).toString()));
    }

    @Test
    void should_return_data_for_last_two_days() {
        List<PlankData> result = this.restTemplate.getForObject("http://localhost:" + port + "/plank/getDataForDays/2",
                List.class);
        assertThat(result).hasSize(1)
                .extracting("user", "date")
                .containsExactly(tuple("ACHINT", now().toString()));
    }

    @Test
    void should_save_new_data() {
        PlankData plankData = new PlankData("MELISSA", now(), 900);
        this.restTemplate.postForObject("http://localhost:" + port + "/plank/postData", plankData, plankData.getClass());
        PlankData result = plankDataDao.getByUserAndDate("MELISSA", now());

        assertThat((result).equals(plankData));
    }

    @Test
    void should_overwrite_data() {
        PlankData plankData = new PlankData("MELISSA", now(), 900);
        PlankData plankData2 = new PlankData("MELISSA", now(), 1900);
        this.restTemplate.postForObject("http://localhost:" + port + "/plank/postData", plankData, plankData.getClass());
        this.restTemplate.postForObject("http://localhost:" + port + "/plank/postData", plankData2, plankData.getClass());
        PlankData result = plankDataDao.getByUserAndDate("MELISSA", now());

        assertThat((result).equals(plankData2));
    }
}