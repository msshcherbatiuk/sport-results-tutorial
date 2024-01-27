package com.ms.sports.protocol;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Value
public class FileData {
    String name;
    String content;
    GameType gameType;

    public FileData(MultipartFile file) throws IOException {
        this.gameType = GameType.parseFileName(file.getOriginalFilename());
        this.content = new String(file.getBytes(), StandardCharsets.UTF_8);
        this.name = file.getOriginalFilename();
    }
}
