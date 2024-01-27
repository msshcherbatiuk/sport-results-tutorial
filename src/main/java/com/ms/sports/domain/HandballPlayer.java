package com.ms.sports.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HandballPlayer extends Player {
    @CsvBindByName(column = "goals made")
    private int goalsMade;
    @CsvBindByName(column = "goals received")
    private int goalsReceived;
}
