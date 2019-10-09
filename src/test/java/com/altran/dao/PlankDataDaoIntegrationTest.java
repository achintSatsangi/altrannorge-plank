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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class PlankDataDaoIntegrationTest {

    @Autowired
    private PlankDataDao plankDataDao;

    @BeforeEach
    void setup() {
        plankDataDao.insert(new PlankData(now(), 100, 2));
        plankDataDao.insert(new PlankData(now(), 500, 3));
        plankDataDao.insert(new PlankData(now(), 200, 4));
    }

    @AfterEach
    void destroy() {
        plankDataDao.deleteAll();
    }

    @Test
    void should_fetch_all_data() {
        List<PlankData> result = plankDataDao.getAllData();
        assertThat(result).hasSize(3)
                .extracting("userId")
                .containsExactly(2, 3, 4);
    }

    @Test
    void should_fetch_data_for_last_2_days_only() {
        plankDataDao.insert(new PlankData(now().minusDays(3), 200, 1));
        plankDataDao.insert(new PlankData(now().minusDays(1), 200, 5));
        List<PlankData> result = plankDataDao.getDataForDays(2);
        assertThat(result).hasSize(4)
                .extracting("userId")
                .containsExactly(5, 2, 3, 4);
    }

    @Test
    void should_fetch_by_name_date() {
        PlankData result = plankDataDao.getByUserAndDate(2, now());
        assertThat(result)
                .extracting("userId", "plankTimeInSeconds")
                .containsExactly(2, 100);
    }

    @Test
    void should_fetch_by_Id() {
        plankDataDao.insertById(new PlankData(999, now().minusDays(1), 12, 2));
        PlankData result = plankDataDao.getDataById(999);
        assertThat(result)
                .extracting("userId", "plankTimeInSeconds")
                .containsExactly(2, 12);
    }

    @Test
    void should_create_by_id() {
        PlankData obj = new PlankData(555, now(), 200, 6);
        PlankData result = plankDataDao.insertById(obj);

        assertThat(result).isEqualTo(obj);

        PlankData fetchResult = plankDataDao.getDataById(555);
        assertThat(fetchResult).isEqualTo(obj);
    }

    @Test
    void should_create() {
        PlankData obj = new PlankData(now(), 200, 6);
        PlankData result = plankDataDao.insert(obj);

        assertThat(result).isEqualTo(obj);

        PlankData fetchResult = plankDataDao.getByUserAndDate(6, now());
        assertThat(fetchResult) .extracting("userId", "date", "plankTimeInSeconds")
                .containsExactly(6, now(), 200);
    }

    @Test
    void should_update() {
        PlankData obj = plankDataDao.getByUserAndDate(2, now());

        PlankData obj1 = new PlankData(obj.getId(), obj.getDate(), 300, obj.getUserId());

        PlankData result = plankDataDao.updateById(obj1);

        assertThat(result).isEqualTo(obj1);

        PlankData fetchResult = plankDataDao.getByUserAndDate(2, now());
        assertThat(fetchResult).isEqualTo(obj1);
    }

    @Test
    void should_throw_EmptyResultDataAccessException_if_data_not_found() {
        assertThrows(EmptyResultDataAccessException.class, () -> plankDataDao.getDataById(4));
    }
}