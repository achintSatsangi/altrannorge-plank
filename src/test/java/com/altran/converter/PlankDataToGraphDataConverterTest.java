package com.altran.converter;

import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class PlankDataToGraphDataConverterTest {

    private PlankDataToGraphDataConverter classToTest = new PlankDataToGraphDataConverter();

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
        GraphData result = classToTest.convert(of(new PlankData(now().minusDays(2), 100, 2), new PlankData(now(), 500, 3)), of(new UserDTO(2, "ACHINT", "Achint Satsangi", null), new UserDTO(3, "RUBEN", "Ruben Dewitte", null)));

        assertThat(result.getLabels()).hasSize(2)
                .contains(now().minusDays(2), now());
        assertThat(result.getDataSets()).hasSize(2)
                .extracting("user.username", "fill", "lineTension", "spanGaps", "pointBorderWidth")
                .contains(tuple("ACHINT", false, 0.1, true, 1), tuple("RUBEN", false, 0.1, true, 1));

        assertThat(result.getDataSets()).hasSize(2);
        assertThat(result.getDataSets().stream().flatMap(d -> d.getData().stream()).collect(Collectors.toList())).contains(100, null, 500);
    }
}