package com.altran.dao;

import com.altran.Application;
import com.altran.model.PlankData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class PlankDataDaoTest {

    @Autowired
    private PlankDataDao plankDataDao;

    @BeforeEach
    void setup() {
        plankDataDao.insert(new PlankData(1, "ACHINT", now(), 100));
        plankDataDao.insert(new PlankData(2, "RUBEN", now(), 500));
        plankDataDao.insert(new PlankData(3, "MELISSA", now(), 200));
    }

    @AfterEach
    void destroy() {
        plankDataDao.deleteAll();
    }

    @Test
    void should_fetch_all_data() {
        List<PlankData> result = plankDataDao.getAllData();
        assertThat(result).hasSize(3)
                .extracting("id", "user")
                .containsExactly(tuple(1, "ACHINT"), tuple(2, "RUBEN"), tuple(3, "MELISSA"));
    }

    @Test
    void should_fetch_data_for_last_2_days_only() {
        plankDataDao.insert(new PlankData(5, "CAMILLA", now().minusDays(3), 200));
        plankDataDao.insert(new PlankData(6, "OLE", now().minusDays(1), 200));
        List<PlankData> result = plankDataDao.getDataForDays(2);
        assertThat(result).hasSize(4)
                .extracting("id", "user")
                .containsExactly(tuple(1, "ACHINT"), tuple(2, "RUBEN"), tuple(3, "MELISSA"),  tuple(6, "OLE"));
    }

    @Test
    void should_fetch_by_id() {
        PlankData result = plankDataDao.getDataById(1);
        assertThat(result)
                .extracting("id", "user")
                .containsExactly(1, "ACHINT");
    }

    @Test
    void should_create() {
        PlankData obj = new PlankData(4, "PK", now(), 200);
        PlankData result = plankDataDao.insert(obj);

        assertThat(result).isEqualTo(obj);

        PlankData fetchResult = plankDataDao.getDataById(4);
        assertThat(fetchResult).isEqualTo(obj);
    }

    @Test
    void should_update() {
        PlankData obj = plankDataDao.getDataById(1);

        PlankData obj1 = new PlankData(obj.getId(), obj.getUser(), obj.getDate(), 300);

        PlankData result = plankDataDao.updateById(obj1);

        assertThat(result).isEqualTo(obj1);

        PlankData fetchResult = plankDataDao.getDataById(1);
        assertThat(fetchResult).isEqualTo(obj1);
    }

    @Test
    void should_throw_EmptyResultDataAccessException_if_data_not_found() {
        assertThrows(EmptyResultDataAccessException.class, () -> plankDataDao.getDataById(4));
    }
}