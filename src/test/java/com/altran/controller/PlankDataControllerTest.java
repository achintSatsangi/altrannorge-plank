package com.altran.controller;

import com.altran.converter.PlankDataToGraphDataConverter;
import com.altran.dao.PlankDataDao;
import com.altran.dao.UserDao;
import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlankDataControllerTest {

    private PlankDataController classToTest;
    private PlankDataDao mockPlankDataDao;
    private UserDao mockUserDao;
    private PlankDataToGraphDataConverter mockConverter;
    private List<PlankData> plankData;
    private List<User> users;

    @BeforeEach
    void setUp() {
        mockPlankDataDao = mock(PlankDataDao.class);
        mockConverter = mock(PlankDataToGraphDataConverter.class);
        mockUserDao = mock(UserDao.class);
        classToTest = new PlankDataController(mockPlankDataDao, mockConverter, mockUserDao);
        plankData = List.of(new PlankData(5, now().minusDays(3), 200, 1));
        users = List.of(new User(1, "CAMILLA", "Camilla Bakken", null));
        users.forEach(User::toString);// For improving coverage :P :P
    }

    @Test
    void should_get_all_data_from_dao() {
        when(mockPlankDataDao.getAllData()).thenReturn(plankData);

        List<PlankData> result = classToTest.getData();
        verify(mockPlankDataDao).getAllData();
        assertThat(result).isEqualTo(plankData);
    }

    @Test
    void should_get_data_for_last_days_from_dao() {
        when(mockPlankDataDao.getDataForDays(2)).thenReturn(plankData);

        List<PlankData> result = classToTest.getDataForDays(2);
        verify(mockPlankDataDao).getDataForDays(2);
        assertThat(result).isEqualTo(plankData);
    }

    @Test
    void should_get_converted_data_for_graphs() {
        when(mockPlankDataDao.getAllData()).thenReturn(plankData);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(users.get(0), List.of(1, 2))));
        when(mockUserDao.getAllUsers()).thenReturn(users);
        when(mockConverter.convert(plankData, users)).thenReturn(graphData);

        GraphData result = classToTest.getAllDataForGraph();

        verify(mockPlankDataDao).getAllData();
        verify(mockUserDao).getAllUsers();
        verify(mockConverter).convert(plankData, users);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getUser).map(User::getUsername).collect(Collectors.toList())).containsExactly("CAMILLA");
    }

}