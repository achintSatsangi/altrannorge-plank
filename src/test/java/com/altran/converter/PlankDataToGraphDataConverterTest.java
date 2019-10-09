package com.altran.converter;

import com.altran.model.GraphData;
import com.altran.model.PlankData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.altran.user.User.ACHINT;
import static com.altran.user.User.RUBEN;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class PlankDataToGraphDataConverterTest {

    private PlankDataToGraphDataConverter classToTest = new PlankDataToGraphDataConverter();

    @Test
    void should_return_empty_graph_data_if_input_is_null() {
        GraphData result = classToTest.convert(null);
        assertThat(result.getLabels()).isEmpty();
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_return_empty_graph_data_if_input_is_empty() {
        GraphData result = classToTest.convert(List.of());
        assertThat(result.getLabels()).isEmpty();
        assertThat(result.getDataSets()).isEmpty();
    }

    @Test
    void should_convert_plank_data_to_graph_data() {
        GraphData result = classToTest.convert(List.of(new PlankData(ACHINT, now().minusDays(2), 100), new PlankData(RUBEN, now(), 500)));

        assertThat(result.getLabels()).hasSize(2)
                .contains(now().minusDays(2), now());
        assertThat(result.getDataSets()).hasSize(2)
                .extracting("user", "fill", "lineTension", "spanGaps", "pointBorderWidth")
                .contains(tuple(ACHINT, false, 0.1, true, 1), tuple(RUBEN, false, 0.1, true, 1));

        assertThat(result.getDataSets()).hasSize(2);
        assertThat(result.getDataSets().stream().flatMap(d -> d.getData().stream()).collect(Collectors.toList())).contains(100, null, 500);
    }
}