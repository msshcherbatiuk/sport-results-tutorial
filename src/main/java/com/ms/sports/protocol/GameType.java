package com.ms.sports.protocol;

import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
public enum GameType {
    BASKETBALL("basketball.csv"),
    HANDBALL("handball.csv"),
    UNKNOWN("unknown");

    private String fileName;

    GameType(String fileName) {
        this.fileName = fileName;
    }

    public static GameType parseFileName(String fileName) {
        try {
            return stream(values()).filter(value -> value.getFileName().equalsIgnoreCase(fileName)).findFirst().orElse(UNKNOWN);
        } catch (Exception ex) {
            return UNKNOWN;
        }
    }
}
