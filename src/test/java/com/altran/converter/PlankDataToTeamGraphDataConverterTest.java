package com.altran.converter;

import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.Team;
import com.altran.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlankDataToTeamGraphDataConverterTest {

    private PlankDataToTeamGraphDataConverter classToTest = new PlankDataToTeamGraphDataConverter();

    @Test
    void should_return_empty_graph_data_if_input_plank_data_is_null() {
        GraphData result = classToTest.convert(null, of(), of());
        assertThat(result.getLabels()).isEmpty();
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_throw_illegal_argument_exception_if_users_is_null() {
        assertThrows(IllegalArgumentException.class, () -> classToTest.convert(of(), null, of()));
    }

    @Test
    void should_throw_illegal_argument_exception_if_teams_is_null() {
        assertThrows(IllegalArgumentException.class, () -> classToTest.convert(of(), of(), null));
    }

    @Test
    void should_return_labels_with_no_datasets_if_plank_data_and_users_are_available_and_no_teams_data() {
        List<PlankData> plankDataList = of(new PlankData(now().minusDays(2), 100, 2),
                new PlankData(now(), 500, 3));
        List<User> users = List.of(new User(1, "CAMILLA", "Camilla Bakken", 1),
                new User(2, "ACHINT", "Achint Satsangi", 1),
                new User(3, "RUBEN", "Ruben", 2),
                new User(4, "MELISSA", "Melissa", 2));

        GraphData result = classToTest.convert(plankDataList, users, of());

        assertThat(result.getLabels()).hasSize(2)
                .contains(now().minusDays(2), now());
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_return_labels_with_empty_datasets_if_plank_data_and_teams_are_available_and_no_users_data() {
        List<PlankData> plankDataList = of(new PlankData(now().minusDays(2), 100, 2),
                new PlankData(now(), 500, 3));
        List<Team> teams = List.of(new Team(1, "Team 1", "Oslo"),
                new Team(2, "Team 2", "Bergen"));
        GraphData result = classToTest.convert(plankDataList, of(), teams);

        assertThat(result.getLabels()).hasSize(2)
                .contains(now().minusDays(2), now());
        assertThat(result.getDataSets()).hasSize(2);
        result.getDataSets().forEach(ds -> assertThat(ds.getData()).containsExactly(null, null));
    }

    @Test
    void should_collate_plottable_graph_data_for_valid_inputs() {
        List<PlankData> plankDataList = of(
                new PlankData(now().minusDays(1), 100, 2),
                new PlankData(now().minusDays(1), 200, 3),
                new PlankData(now().minusDays(1), 50, 1),
                new PlankData(now(), 100, 2),
                new PlankData(now(), 200, 3),
                new PlankData(now(), 50, 4),
                new PlankData(now().minusDays(2), 50, 4)
                );
        List<Team> teams = List.of(new Team(1, "Team 1", "Oslo"),
                new Team(2, "Team 2", "Bergen"));
        List<User> users = List.of(new User(1, "CAMILLA", "Camilla Bakken", 1),
                new User(2, "ACHINT", "Achint Satsangi", 1),
                new User(3, "RUBEN", "Ruben", 2),
                new User(4, "MELISSA", "Melissa", 2));

        GraphData result = classToTest.convert(plankDataList, users, teams);

        assertThat(result.getLabels()).hasSize(3)
                .contains(now().minusDays(2), now().minusDays(1), now());

        List<DataSet> resultDataSets = result.getDataSets();

        assertThat(resultDataSets).hasSize(2);

        assertThat(resultDataSets.get(0).getLabel().equals("Team 1"));
        assertThat(resultDataSets.get(0).getData()).hasSize(3)
                .containsExactly(null, 150, 100);

        assertThat(resultDataSets.get(1).getLabel().equals("Team 2"));
        assertThat(resultDataSets.get(1).getData()).hasSize(3)
                .containsExactly(50, 200, 250);
    }


}