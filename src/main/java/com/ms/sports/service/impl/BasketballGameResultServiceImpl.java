package com.ms.sports.service.impl;

import com.ms.sports.config.BasketballConfig;
import com.ms.sports.config.GameConfig;
import com.ms.sports.protocol.GameType;
import com.ms.sports.service.GameResultService;
import com.ms.sports.domain.BasketballPlayer;
import com.ms.sports.domain.Player;
import com.ms.sports.protocol.GameResult;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Component
public class BasketballGameResultServiceImpl implements GameResultService<BasketballPlayer> {
    @SuppressWarnings("unchecked")
    @Override
    public GameResult calculate(List<? extends Player> gameStats) {
        final List<BasketballPlayer> playersList = (List<BasketballPlayer>) gameStats;

        String teamWhoWon = calculateTeamWhoWon(playersList);
        Map<String, Integer> playersResults = calculatePlayersResults(playersList, teamWhoWon);
        String gameMVP = calculateGameMVP(playersResults);

        return new GameResult(playersResults, teamWhoWon, gameMVP, GameType.BASKETBALL);
    }

    private int calculatePlayerScore(BasketballPlayer player, String teamWhoWon) {
        return player.getScoredPoints() * BasketballConfig.SCORED_POINT
                + player.getAssists() * BasketballConfig.ASSIST
                + player.getRebounds() * BasketballConfig.REBOUND
                + (teamWhoWon.equals(player.getTeamName()) ? GameConfig.WON_TEAM_ADDITIONAL_POINTS : 0);
    }

    private String calculateTeamWhoWon(List<BasketballPlayer> playersList) {
        Map<String, Integer> teamsResult = playersList.stream()
                .collect(groupingBy(BasketballPlayer::getTeamName, summingInt(BasketballPlayer::getScoredPoints)));
        return Collections.max(teamsResult.entrySet(), comparingInt(Map.Entry::getValue)).getKey();
    }

    private Map<String, Integer> calculatePlayersResults(List<BasketballPlayer> playersList, String teamWhoWon) {
        Map<String, Integer> gameResult = new HashMap<>();
        for (BasketballPlayer player : playersList) {
            gameResult.put(player.getPlayerName(), calculatePlayerScore(player, teamWhoWon));
        }
        return gameResult;
    }

    private String calculateGameMVP(Map<String, Integer> playersResults) {
        return Collections.max(playersResults.entrySet(), comparingInt(Map.Entry::getValue)).getKey();
    }
}
