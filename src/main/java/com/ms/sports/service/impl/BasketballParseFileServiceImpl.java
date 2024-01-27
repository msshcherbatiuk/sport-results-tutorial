package com.ms.sports.service.impl;

import com.ms.sports.domain.BasketballPlayer;
import com.ms.sports.protocol.FileData;
import com.ms.sports.service.ParseFileService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasketballParseFileServiceImpl extends ParseFileServiceBase implements ParseFileService<BasketballPlayer> {
    @Override
    public List<BasketballPlayer> parseFile(FileData data) throws Exception {
        List<BasketballPlayer> playersList = parseCsvString(data.getContent(), BasketballPlayer.class);
        validate(playersList);
        return playersList;
    }
}
