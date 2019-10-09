package com.altran.converter;

import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.UserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class PlankDataToGraphDataConverter {

    public GraphData convert(List<PlankData> plankDataList, List<UserDTO> users) {
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
                .map(userId -> prepareDataSet(userId, labels, plankDataList, users.stream().filter(u -> u.getId() == userId).findFirst().orElseThrow(IllegalStateException::new)))
                .collect(toList());

        return new GraphData(labels, dataSets);
    }

    private DataSet prepareDataSet(Integer userId, List<LocalDate> labels, List<PlankData> plankDataList, UserDTO userDTO) {
        List<Integer> plankTimes = labels.stream()
                .map(label -> getTimeForUserForDate(userDTO, label, plankDataList))
                .collect(toList());
        return new DataSet(userDTO, plankTimes);
    }

    private Integer getTimeForUserForDate(UserDTO userDTO, LocalDate label, List<PlankData> plankDataList) {
        return plankDataList.stream()
                .filter(plankData -> plankData.getUserId().equals(userDTO.getId()) && plankData.getDate().equals(label))
                .map(PlankData::getPlankTimeInSeconds)
                .findFirst()
                .orElse(null);
    }
}
