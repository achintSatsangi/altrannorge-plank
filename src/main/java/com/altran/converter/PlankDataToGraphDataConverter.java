package com.altran.converter;

import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class PlankDataToGraphDataConverter {

    public GraphData convert(List<PlankData> plankDataList) {
        if(Objects.isNull(plankDataList) || plankDataList.isEmpty()) {
            return new GraphData(Set.of(), List.of());
        }

        Set<LocalDate> labels = plankDataList.stream()
                .map(PlankData::getDate)
                .collect(toSet());

        Set<User> users = plankDataList.stream()
                .map(PlankData::getUser)
                .collect(toSet());

        List<DataSet> dataSets = users.stream()
                .map(user -> prepareDataSet(user, labels, plankDataList))
                .collect(toList());

        return new GraphData(labels, dataSets);
    }

    private DataSet prepareDataSet(User user, Set<LocalDate> labels, List<PlankData> plankDataList) {
        List<Integer> plankTimes = labels.stream()
                .map(label -> getTimeForUserForDate(user, label, plankDataList))
                .collect(toList());
        return new DataSet(user, plankTimes);
    }

    private Integer getTimeForUserForDate(User user, LocalDate label, List<PlankData> plankDataList) {
        return plankDataList.stream()
                .filter(plankData -> plankData.getUser().equals(user) && plankData.getDate().equals(label))
                .map(PlankData::getPlankTimeInSeconds)
                .findFirst()
                .orElse(null);
    }
}
