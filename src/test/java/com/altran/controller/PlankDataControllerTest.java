package com.altran.controller;

import com.altran.dao.PlankDataDao;
import com.altran.model.PlankData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.altran.user.User.CAMILLA;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlankDataControllerTest {

    private PlankDataController classToTest;
    private PlankDataDao mockPlankDataDao;

    @BeforeEach
    void setUp() {
        mockPlankDataDao = mock(PlankDataDao.class);
        classToTest = new PlankDataController(mockPlankDataDao);
    }

    @Test
    void should_get_all_data_from_dao() {
        List<PlankData> toBeReturnedFromDao = List.of(new PlankData(5, CAMILLA, now().minusDays(3), 200));
        when(mockPlankDataDao.getAllData()).thenReturn(toBeReturnedFromDao);

        List<PlankData> result = classToTest.getData();
        Mockito.verify(mockPlankDataDao).getAllData();
        assertThat(result).isEqualTo(toBeReturnedFromDao);
    }

    @Test
    void should_get_data_for_last_days_from_dao() {
        List<PlankData> toBeReturnedFromDao = List.of(new PlankData(5, CAMILLA, now().minusDays(3), 200));
        when(mockPlankDataDao.getDataForDays(2)).thenReturn(toBeReturnedFromDao);

        List<PlankData> result = classToTest.getDataForDays(2);
        Mockito.verify(mockPlankDataDao).getDataForDays(2);
        assertThat(result).isEqualTo(toBeReturnedFromDao);
    }
}