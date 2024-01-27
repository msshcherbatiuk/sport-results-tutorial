package com.ms.sports.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NonNull;

@Data
public class Player {
    @NonNull
    @CsvBindByName(column = "player name", required = true)
    private String playerName;
    @NonNull
    @CsvBindByName(column = "nickname", required = true)
    private String nickName;
    @NonNull
    @CsvBindByName(column = "number", required = true)
    private Integer playerNumber;
    @NonNull
    @CsvBindByName(column = "team name", required = true)
    private String teamName;

    public Player() {
    }
}
