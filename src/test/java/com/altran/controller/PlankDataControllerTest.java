package com.altran.controller;

import com.altran.converter.PlankDataToGraphDataConverter;
import com.altran.dao.PlankDataDao;
import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.altran.user.User.CAMILLA;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlankDataControllerTest {

    private PlankDataController classToTest;
    private PlankDataDao mockPlankDataDao;
    private PlankDataToGraphDataConverter mockConverter;
    private List<PlankData> toBeReturnedFromDao;

    @BeforeEach
    void setUp() {
        mockPlankDataDao = mock(PlankDataDao.class);
        mockConverter = mock(PlankDataToGraphDataConverter.class);
        classToTest = new PlankDataController(mockPlankDataDao, mockConverter);
        toBeReturnedFromDao = List.of(new PlankData(5, CAMILLA, now().minusDays(3), 200));
    }

    @Test
    void should_get_all_data_from_dao() {
        when(mockPlankDataDao.getAllData()).thenReturn(toBeReturnedFromDao);

        List<PlankData> result = classToTest.getData();
        verify(mockPlankDataDao).getAllData();
        assertThat(result).isEqualTo(toBeReturnedFromDao);
    }

    @Test
    void should_get_data_for_last_days_from_dao() {
        when(mockPlankDataDao.getDataForDays(2)).thenReturn(toBeReturnedFromDao);

        List<PlankData> result = classToTest.getDataForDays(2);
        verify(mockPlankDataDao).getDataForDays(2);
        assertThat(result).isEqualTo(toBeReturnedFromDao);
    }

    @Test
    void should_get_converted_data_for_graphs() {
        when(mockPlankDataDao.getAllData()).thenReturn(toBeReturnedFromDao);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(CAMILLA, List.of(1, 2))));
        when(mockConverter.convert(toBeReturnedFromDao)).thenReturn(graphData);

        GraphData result = classToTest.getAllDataForGraph();

        verify(mockPlankDataDao).getAllData();
        verify(mockConverter).convert(toBeReturnedFromDao);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getUser).collect(Collectors.toList())).containsExactly(CAMILLA);
    }

    @Test
    void should_get_converted_data_for_graphs_for_input_days() {
        int inputDays = 2;
        when(mockPlankDataDao.getDataForDays(inputDays)).thenReturn(toBeReturnedFromDao);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(CAMILLA, List.of(1, 2))));
        when(mockConverter.convert(toBeReturnedFromDao)).thenReturn(graphData);

        GraphData result = classToTest.getDataForGraph(inputDays);

        verify(mockPlankDataDao).getDataForDays(inputDays);
        verify(mockConverter).convert(toBeReturnedFromDao);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getUser).collect(Collectors.toList())).containsExactly(CAMILLA);
    }

}