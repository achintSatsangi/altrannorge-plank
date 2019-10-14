package com.altran.converter;

import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class PlankDataToUserGraphDataConverterTest {

    private PlankDataToUserGraphDataConverter classToTest = new PlankDataToUserGraphDataConverter();

    @Test
    void should_return_empty_graph_data_if_input_is_null() {
        GraphData result = classToTest.convert(null, null);
        assertThat(result.getLabels()).isEmpty();
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_return_empty_graph_data_if_input_is_empty() {
        GraphData result = classToTest.convert(of(), of());
        assertThat(result.getLabels()).isEmpty();
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_convert_plank_data_to_graph_data() {
        List<PlankData> plankDataList = of(new PlankData(now().minusDays(2), 100, 2),
                new PlankData(now(), 500, 3));
        List<User> users = of(new User(2, "ACHINT", "Achint Satsangi", null),
                new User(3, "RUBEN", "Ruben Dewitte", null));
        GraphData result = classToTest.convert(plankDataList, users);

        assertThat(result.getLabels()).hasSize(2)
                .contains(now().minusDays(2), now());
        assertThat(result.getDataSets()).hasSize(2)
                .extracting("label", "fill", "lineTension", "spanGaps", "pointBorderWidth")
                .contains(tuple("Achint Satsangi", false, 0.1, true, 1), tuple("Ruben Dewitte", false, 0.1, true, 1));

        assertThat(result.getDataSets()).hasSize(2);
        assertThat(result.getDataSets().stream().flatMap(d -> d.getData().stream()).collect(Collectors.toList()))
                .contains(100, null, 500);
    }
}