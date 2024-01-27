package com.ms.sports.factory;

import lombok.RequiredArgsConstructor;
import com.ms.sports.domain.BasketballPlayer;
import com.ms.sports.domain.HandballPlayer;
import com.ms.sports.domain.Player;
import com.ms.sports.protocol.FileData;
import com.ms.sports.service.GameResultService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameCalculationServiceFactory {

    private final GameResultService<BasketballPlayer> basketballGameResultService;
    private final GameResultService<HandballPlayer> handballPlayerGameResultService;

    public GameResultService<? extends Player> getCalculationService(FileData fileData) throws Exception {
        switch (fileData.getGameType()) {
            case BASKETBALL:
                return basketballGameResultService;
            case HANDBALL:
                return handballPlayerGameResultService;
            case UNKNOWN:
            default:
                throw new Exception("Unable to locate necessary service");
        }
    }
}
