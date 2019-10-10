package com.altran.converter;

import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class PlankDataToGraphDataConverter {

    public GraphData convert(List<PlankData> plankDataList, List<User> users) {
        if(isNull(plankDataList) || plankDataList.isEmpty()) {
            return new GraphData(List.of(), List.of());
        }

        List<LocalDate> labels = plankDataList.stream()
                .map(PlankData::getDate)
                .distinct().sorted().collect(toList());

        Set<Integer> userIds = plankDataList.stream()
                .map(PlankData::getUserId)
                .collect(toSet());

        List<DataSet> dataSets = userIds.stream()
                .map(userId -> prepareDataSet(labels, plankDataList, users.stream().filter(u -> u.getId() == userId).findFirst().orElseThrow(IllegalStateException::new)))
                .collect(toList());

        return new GraphData(labels, dataSets);
    }

    private DataSet prepareDataSet(List<LocalDate> labels, List<PlankData> plankDataList, User user) {
        List<Integer> plankTimes = labels.stream()
                .map(label -> getTimeForUserForDate(user, label, plankDataList))
                .collect(toList());
        return new DataSet(user, plankTimes);
    }

    private Integer getTimeForUserForDate(User user, LocalDate label, List<PlankData> plankDataList) {
        return plankDataList.stream()
                .filter(plankData -> plankData.getUserId().equals(user.getId()) && plankData.getDate().equals(label))
                .map(PlankData::getPlankTimeInSeconds)
                .findFirst()
                .orElse(null);
    }
}
