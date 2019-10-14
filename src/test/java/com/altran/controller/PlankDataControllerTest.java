package com.altran.controller;

import com.altran.converter.PlankDataToTeamGraphDataConverter;
import com.altran.converter.PlankDataToUserGraphDataConverter;
import com.altran.dao.PlankDataDao;
import com.altran.dao.TeamDao;
import com.altran.dao.UserDao;
import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.Team;
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
    private TeamDao mockTeamDao;
    private PlankDataToUserGraphDataConverter mockUserGraphDataConverter;
    private PlankDataToTeamGraphDataConverter mockTeamGraphDataConverter;
    private List<PlankData> plankData;
    private List<User> users;
    private List<Team> teams;

    @BeforeEach
    void setUp() {
        mockPlankDataDao = mock(PlankDataDao.class);
        mockUserGraphDataConverter = mock(PlankDataToUserGraphDataConverter.class);
        mockTeamGraphDataConverter = mock(PlankDataToTeamGraphDataConverter.class);
        mockUserDao = mock(UserDao.class);
        mockTeamDao = mock(TeamDao.class);
        classToTest = new PlankDataController(mockPlankDataDao, mockUserGraphDataConverter, mockTeamGraphDataConverter, mockUserDao, mockTeamDao);
        plankData = List.of(new PlankData(5, now().minusDays(3), 200, 1));
        users = List.of(new User(1, "CAMILLA", "Camilla Bakken", 1));
        users.forEach(User::toString);// For improving coverage :P :P
        teams = List.of(new Team(1, "Team 1", "Oslo"));
        teams.forEach(Team::toString);
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
    void should_get_converted_data_for_user_graphs() {
        when(mockPlankDataDao.getAllData()).thenReturn(plankData);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(users.get(0).getVisibleName(), List.of(1, 2))));
        when(mockUserDao.getAllUsers()).thenReturn(users);
        when(mockUserGraphDataConverter.convert(plankData, users)).thenReturn(graphData);

        GraphData result = classToTest.userDataForGraph();

        verify(mockPlankDataDao).getAllData();
        verify(mockUserDao).getAllUsers();
        verify(mockUserGraphDataConverter).convert(plankData, users);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getLabel).collect(Collectors.toList())).containsExactly("Camilla Bakken");
    }

    @Test
    void should_get_converted_data_for_user_graphs_for_input_days() {
        int inputDays = 2;
        when(mockPlankDataDao.getDataForDays(inputDays)).thenReturn(plankData);
        when(mockUserDao.getAllUsers()).thenReturn(users);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(users.get(0).getVisibleName(), List.of(1, 2))));
        when(mockUserGraphDataConverter.convert(plankData, users)).thenReturn(graphData);

        GraphData result = classToTest.userDataForGraph(inputDays);

        verify(mockPlankDataDao).getDataForDays(inputDays);
        verify(mockUserDao).getAllUsers();
        verify(mockUserGraphDataConverter).convert(plankData, users);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getLabel).collect(Collectors.toList())).containsExactly("Camilla Bakken");
    }

    @Test
    void should_get_converted_data_for_team_graphs() {
        when(mockPlankDataDao.getAllData()).thenReturn(plankData);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(teams.get(0).getName(), List.of(1, 2))));
        when(mockUserDao.getAllUsers()).thenReturn(users);
        when(mockTeamDao.getAllTeams()).thenReturn(teams);
        when(mockTeamGraphDataConverter.convert(plankData, users, teams)).thenReturn(graphData);

        GraphData result = classToTest.teamDataForGraph();

        verify(mockPlankDataDao).getAllData();
        verify(mockUserDao).getAllUsers();
        verify(mockTeamGraphDataConverter).convert(plankData, users, teams);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getLabel).collect(Collectors.toList())).containsExactly("Team 1");
    }

    @Test
    void should_get_converted_data_for_team_graphs_for_input_days() {
        int inputDays = 2;
        when(mockPlankDataDao.getDataForDays(inputDays)).thenReturn(plankData);
        GraphData graphData = new GraphData(List.of(now()), List.of(new DataSet(teams.get(0).getName(), List.of(1, 2))));
        when(mockUserDao.getAllUsers()).thenReturn(users);
        when(mockTeamDao.getAllTeams()).thenReturn(teams);
        when(mockTeamGraphDataConverter.convert(plankData, users, teams)).thenReturn(graphData);

        GraphData result = classToTest.teamDataForGraph(inputDays);

        verify(mockPlankDataDao).getDataForDays(inputDays);
        verify(mockUserDao).getAllUsers();
        verify(mockTeamGraphDataConverter).convert(plankData, users, teams);

        assertThat(result.getLabels()).containsExactly(now());
        assertThat(result.getDataSets().stream().map(DataSet::getLabel).collect(Collectors.toList())).containsExactly("Team 1");
    }


}