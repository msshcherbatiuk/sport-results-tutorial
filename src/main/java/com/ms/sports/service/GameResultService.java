package com.ms.sports.service;

import com.ms.sports.domain.Player;
import com.ms.sports.protocol.GameResult;

import java.util.List;

public interface GameResultService<T extends Player> {
    GameResult calculate(List<? extends Player> gameStats);
}
