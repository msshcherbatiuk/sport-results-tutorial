package com.ms.sports.service;

import com.ms.sports.domain.Player;
import com.ms.sports.protocol.FileData;

import java.util.List;

public interface ParseFileService<T extends Player> {
    List<T> parseFile(FileData data) throws Exception;
}
