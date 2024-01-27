package com.ms.sports.protocol;

import lombok.Value;

import java.util.Map;

@Value
public class GameResult {
    Map<String, Integer> result;
    String wonTeam;
    String gameMVP;
    GameType gameType;

    public GameResult(Map<String, Integer> result, String wonTeam, String gameMVP, GameType gameType) {
        this.result = result;
        this.wonTeam = wonTeam;
        this.gameMVP = gameMVP;
        this.gameType = gameType;
    }
}
