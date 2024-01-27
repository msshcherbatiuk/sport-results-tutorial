package com.ms.sports.service.impl;

import com.ms.sports.domain.HandballPlayer;
import com.ms.sports.protocol.FileData;
import com.ms.sports.service.ParseFileService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandballParseFileServiceImpl extends ParseFileServiceBase implements ParseFileService<HandballPlayer> {
    @Override
    public List<HandballPlayer> parseFile(FileData data) throws Exception {
        List<HandballPlayer> playersList = parseCsvString(data.getContent(), HandballPlayer.class);
        validate(playersList);
        return playersList;
    }
}
