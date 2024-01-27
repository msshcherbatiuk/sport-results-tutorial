package com.ms.sports.service.impl;

import com.ms.sports.config.GameConfig;
import com.ms.sports.config.HandballConfig;
import com.ms.sports.protocol.GameType;
import com.ms.sports.domain.HandballPlayer;
import com.ms.sports.domain.Player;
import com.ms.sports.protocol.GameResult;
import com.ms.sports.service.GameResultService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Component
public class HandballGameResultServiceImpl implements GameResultService<HandballPlayer> {
    @SuppressWarnings("unchecked")
    @Override
    public GameResult calculate(List<? extends Player> gameStats) {
        final List<HandballPlayer> playersList = (List<HandballPlayer>) gameStats;

        String teamWhoWon = calculateTeamWhoWon(playersList);
        Map<String, Integer> playersResults = calculatePlayersResults(playersList, teamWhoWon);
        String gameMVP = calculateGameMVP(playersResults);

        return new GameResult(playersResults, teamWhoWon, gameMVP, GameType.HANDBALL);
    }

    private int calculatePlayerScore(HandballPlayer player, String teamWhoWon) {
        return player.getGoalsMade() * HandballConfig.GOALS_MADE
                + player.getGoalsReceived() * HandballConfig.GOALS_RECEIVED
                + (teamWhoWon.equals(player.getTeamName()) ? GameConfig.WON_TEAM_ADDITIONAL_POINTS : 0);
    }

    private String calculateTeamWhoWon(List<HandballPlayer> playersList) {
        Map<String, Integer> teamsResult = playersList.stream()
                .collect(groupingBy(HandballPlayer::getTeamName, summingInt(HandballPlayer::getGoalsMade)));
        return Collections.max(teamsResult.entrySet(), comparingInt(Map.Entry::getValue)).getKey();
    }

    private Map<String, Integer> calculatePlayersResults(List<HandballPlayer> playersList, String teamWhoWon) {
        Map<String, Integer> gameResult = new HashMap<>();
        for (HandballPlayer player : playersList) {
            gameResult.put(player.getPlayerName(), calculatePlayerScore(player, teamWhoWon));
        }
        return gameResult;
    }

    private String calculateGameMVP(Map<String, Integer> playersResults) {
        return Collections.max(playersResults.entrySet(), comparingInt(Map.Entry::getValue)).getKey();
    }
}
