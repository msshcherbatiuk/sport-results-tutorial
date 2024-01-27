package com.ms.sports.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class HandballConfig {
    int goalsMade;
    int goalsReceived;

    @JsonCreator
    public HandballConfig(@JsonProperty("goalsMade") int goalsMade,
                          @JsonProperty("goalsReceived") int goalsReceived) {
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }
}
