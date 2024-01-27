package com.ms.sports.service.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ms.sports.domain.Player;

import java.io.StringReader;
import java.util.List;

import static java.util.Objects.isNull;


public abstract class ParseFileServiceBase {
    private static final char SEPARATOR = ';';

    protected <T extends Player> List<T> parseCsvString(String content, Class<T> clazz) {
        CSVReader reader = buildReader(content);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withType(clazz).build();
        return csvToBean.parse();
    }

    private CSVReader buildReader(String content) {
        CSVParser parser = new CSVParserBuilder().withSeparator(SEPARATOR).withIgnoreLeadingWhiteSpace(true).build();
        return new CSVReaderBuilder(new StringReader(content))
                .withCSVParser(parser).build();
    }

    protected void validate(List<?> playersList) throws Exception {
        if (isNull(playersList) || playersList.isEmpty())
            throw new Exception("Unable to parse the game file");
    }
}
