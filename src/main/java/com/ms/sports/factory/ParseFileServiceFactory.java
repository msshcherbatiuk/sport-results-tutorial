package com.ms.sports.factory;

import lombok.RequiredArgsConstructor;
import com.ms.sports.domain.BasketballPlayer;
import com.ms.sports.domain.HandballPlayer;
import com.ms.sports.protocol.FileData;
import com.ms.sports.service.ParseFileService;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class ParseFileServiceFactory {
    private final ParseFileService<BasketballPlayer> basketballParseFileService;
    private final ParseFileService<HandballPlayer> handballParseFileService;

    public ParseFileService<?> getParseService(FileData fileData) throws Exception {
        if (isFileNameCorrect(fileData.getName()))
            switch (fileData.getGameType()) {
                case BASKETBALL:
                    return basketballParseFileService;
                case HANDBALL:
                    return handballParseFileService;
                case UNKNOWN:
                default:
            }
        throw new Exception("Unable to locate necessary service");
    }

    private boolean isFileNameCorrect(String fileName) {
        return nonNull(fileName) && fileName.endsWith("csv");
    }
}
