package com.ms.sports.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasketballPlayer extends Player {

    @NonNull
    @CsvBindByName(column = "scored points", required = true)
    private Integer scoredPoints;
    @NonNull
    @CsvBindByName(column = "rebounds", required = true)
    private Integer rebounds;
    @NonNull
    @CsvBindByName(column = "assists", required = true)
    private Integer assists;

    public BasketballPlayer() {
    }
}
