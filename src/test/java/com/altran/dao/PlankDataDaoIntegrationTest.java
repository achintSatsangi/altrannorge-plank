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
        plankDataDao.insert(new PlankData(null, "ACHINT", now(), 100));
        plankDataDao.insert(new PlankData(null, "RUBEN", now(), 500));
        plankDataDao.insert(new PlankData(null, "MELISSA", now(), 200));
    }

    @AfterEach
    void destroy() {
        plankDataDao.deleteAll();
    }

    @Test
    void should_fetch_all_data() {
        List<PlankData> result = plankDataDao.getAllData();
        assertThat(result).hasSize(3)
                .extracting("user")
                .containsExactly("ACHINT", "RUBEN", "MELISSA");
    }

    @Test
    void should_fetch_data_for_last_2_days_only() {
        plankDataDao.insert(new PlankData(null, "CAMILLA", now().minusDays(3), 200));
        plankDataDao.insert(new PlankData(null, "OLE", now().minusDays(1), 200));
        List<PlankData> result = plankDataDao.getDataForDays(2);
        assertThat(result).hasSize(4)
                .extracting("user")
                .containsExactly("ACHINT", "RUBEN", "MELISSA", "OLE");
    }

    @Test
    void should_fetch_by_name_date() {
        PlankData result = plankDataDao.getByUserAndDate("ACHINT", now());
        assertThat(result)
                .extracting("user")
                .containsExactly("ACHINT");
    }

    @Test
    void should_fetch_by_Id() {
        plankDataDao.insertById(new PlankData(999, "ACHINT", now().minusDays(1), 12));
        PlankData result = plankDataDao.getDataById(999);
        assertThat(result)
                .extracting("user")
                .containsExactly("ACHINT");
    }

    @Test
    void should_create_by_id() {
        PlankData obj = new PlankData(555, "PK", now(), 200);
        PlankData result = plankDataDao.insertById(obj);

        assertThat(result).isEqualTo(obj);

        PlankData fetchResult = plankDataDao.getDataById(555);
        assertThat(fetchResult).isEqualTo(obj);
    }

    @Test
    void should_create() {
        PlankData obj = new PlankData(null, "PK", now(), 200);
        PlankData result = plankDataDao.insert(obj);

        assertThat(result).isEqualTo(obj);

        PlankData fetchResult = plankDataDao.getByUserAndDate("PK", now());
        assertThat(fetchResult) .extracting("user", "date", "plankTimeInSeconds")
                .containsExactly("PK", now(), 200);
    }

    @Test
    void should_update() {
        PlankData obj = plankDataDao.getByUserAndDate("ACHINT", now());

        PlankData obj1 = new PlankData(obj.getId(), obj.getUser(), obj.getDate(), 300);

        PlankData result = plankDataDao.updateById(obj1);

        assertThat(result).isEqualTo(obj1);

        PlankData fetchResult = plankDataDao.getByUserAndDate("ACHINT", now());
        assertThat(fetchResult).isEqualTo(obj1);
    }

    @Test
    void should_throw_EmptyResultDataAccessException_if_data_not_found() {
        assertThrows(EmptyResultDataAccessException.class, () -> plankDataDao.getDataById(4));
    }
}