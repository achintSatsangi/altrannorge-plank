package com.altran.converter;

import com.altran.model.DataSet;
import com.altran.model.GraphData;
import com.altran.model.PlankData;
import com.altran.user.Team;
import com.altran.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class PlankDataToTeamGraphDataConverter {

    public GraphData convert(List<PlankData> plankDataList, List<User> users, List<Team> teams) {
        if(isNull(users) || isNull(teams)) {
            throw new IllegalArgumentException("Teams or User list can not be null. Teams : " + teams + ", Users : " + users);
        }

        if(isNull(plankDataList) || plankDataList.isEmpty()) {
            return new GraphData(List.of(), List.of());
        }

        List<LocalDate> labels = plankDataList.stream()
                .map(PlankData::getDate)
                .distinct().sorted().collect(toList());

        List<DataSet> dataSets = teams.stream()
                .map(team -> prepareDataSet(labels, plankDataList, team, users))
                .collect(toList());

        return new GraphData(labels, dataSets);
    }

    private DataSet prepareDataSet(List<LocalDate> labels, List<PlankData> plankDataList, Team team, List<User> users) {
        List<Integer> userIdsForTeam = users.stream()
                .filter(u -> u.getTeamId().equals(team.getId()))
                .map(User::getId)
                .collect(toList());

        List<Integer> plankTimes = labels.stream()
                .map(label -> getTimeForTeamForDate(userIdsForTeam, label, plankDataList))
                .collect(toList());

        return new DataSet(team.getName(), plankTimes);
    }

    private Integer getTimeForTeamForDate(List<Integer> userIdsForTeam, LocalDate label, List<PlankData> plankDataList) {
        List<Integer> teamTimes = plankDataList.stream()
                .filter(plankData -> plankData.getDate().equals(label))
                .filter(plankData -> userIdsForTeam.contains(plankData.getUserId()))
                .map(PlankData::getPlankTimeInSeconds)
                .collect(toList());

        return teamTimes.isEmpty() ? null : teamTimes.stream().reduce(0, Integer::sum);
    }
}
